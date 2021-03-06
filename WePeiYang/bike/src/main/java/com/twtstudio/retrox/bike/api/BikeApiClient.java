package com.twtstudio.retrox.bike.api;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.twtstudio.retrox.bike.model.BikeAnnouncement;
import com.twtstudio.retrox.bike.model.BikeAuth;
import com.twtstudio.retrox.bike.model.BikeCard;
import com.twtstudio.retrox.bike.model.BikeRecord;
import com.twtstudio.retrox.bike.model.BikeStation;
import com.twtstudio.retrox.bike.model.BikeUserInfo;
import com.twtstudio.retrox.bike.utils.PrefUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

import static okhttp3.internal.platform.Platform.INFO;

/**
 * Created by jcy on 2016/8/6.
 */

public class BikeApiClient {

    protected Retrofit mRetrofit;

    protected Map<Object, CompositeSubscription> mSubscriptionsMap = new HashMap<>();

    private BikeApi mService;

    private AuthHelper mTokenHelper;

    private BikeApiClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(message -> {
            if (message.startsWith("{")&&message.length()<100){
                Logger.json(message);
            }else {
                Platform.get().log(INFO, message, null);
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(sRequsetInterceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://bike.twtstudio.com/api.php/")
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mService = mRetrofit.create(BikeApi.class);
        mTokenHelper = new AuthHelper(mService);
    }

    private static class SingletonHolder {
        private static final BikeApiClient INSTANCE = new BikeApiClient();
    }

    public static BikeApiClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public BikeApi getService(){
        return mService;
    }

    protected static Interceptor sRequsetInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originRequset = chain.request();
            //header
            Request.Builder builder = originRequset.newBuilder()
                    .addHeader("Accept", "application/json");
            //auth---no token
            List<String> pathList = originRequset.url().pathSegments();
            if (!pathList.contains("auth")) {
                //add token
                if (originRequset.body() instanceof FormBody) {
                    FormBody.Builder newFormBody = new FormBody.Builder();
                    FormBody oldFormBody = (FormBody) originRequset.body();
                    for (int i = 0; i < oldFormBody.size(); i++) {
                        newFormBody.addEncoded(oldFormBody.encodedName(i), oldFormBody.encodedValue(i));
                    }
                    String bike_token = PrefUtils.getBikeToken();
                    //bike_token.replace("+"," ");
                    newFormBody.addEncoded("auth_token", bike_token);
                    Log.d("api", "intercept: " + PrefUtils.getBikeToken());
                    builder.method(originRequset.method(), newFormBody.build());
                }

            }
            Request request = builder.build();

            return chain.proceed(request);
        }
    };

    public void unSubscribe(Object tag) {
        if (mSubscriptionsMap.containsKey(tag)) {
            CompositeSubscription subscriptions = mSubscriptionsMap.get(tag);
            subscriptions.unsubscribe();
            mSubscriptionsMap.remove(tag);
        }
    }

    protected void addSubscription(Object tag, Subscription subscription) {
        if (tag == null) {
            return;
        }
        CompositeSubscription subscriptions;
        if (mSubscriptionsMap.containsKey(tag)) {
            subscriptions = mSubscriptionsMap.get(tag);
        } else {
            subscriptions = new CompositeSubscription();
        }
        subscriptions.add(subscription);
        mSubscriptionsMap.put(tag, subscriptions);
    }

    public void getBikeToken(Object tag, Subscriber subscriber, String wpy_token) {
        Subscription subscription = mService.getBikeToken(wpy_token)
                .retryWhen(mTokenHelper)
                .map(new BikeResponseTransformer<BikeAuth>())
                .compose(ApiUtils.<BikeAuth>applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }

    public void getBikeCard(Object tag, Subscriber subscriber, String num) {
        Subscription subscription = mService.getBikeCard(num)
                .retryWhen(mTokenHelper)
                .map(new BikeResponseTransformer<List<BikeCard>>())
                .compose(ApiUtils.<List<BikeCard>>applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }

    public void getStationStatus(Object tag, Subscriber subscriber, String id) {
        Subscription subscription = mService.getStationStaus(id)
                .map(new BikeResponseTransformer<List<BikeStation>>())
                .compose(ApiUtils.<List<BikeStation>>applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }

    public void bindBikeCard(Object tag, Subscriber subscriber, String id, String sign) {
        Subscription subscription = mService.bindBikeCard(id, sign)
                .retryWhen(mTokenHelper)
                .map(new BikeResponseTransformer<String>())
                .compose(ApiUtils.<String>applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }

    public void getUserInfo(Object tag, Subscriber subscriber) {
        Subscription subscription = mService.getUserInfo("fake")
                .retryWhen(mTokenHelper)
                .map(new BikeResponseTransformer<BikeUserInfo>())
                .compose(ApiUtils.<BikeUserInfo>applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }

    public void cacheStationStatus(Object tag, Subscriber subscriber) {
        Subscription subscription = mService.cacheStationStaus()
                .repeatWhen(v -> v.delay(90, TimeUnit.SECONDS))
                .map(new BikeResponseTransformer<List<BikeStation>>())
                .compose(ApiUtils.applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }

    public void getAnnouncement(Object tag, Subscriber subscriber) {
        Subscription subscription = mService.getBikeAnnouncement()
                .map(new BikeResponseTransformer<List<BikeAnnouncement>>())
                .compose(ApiUtils.applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }

    public void getBikeRecord(Object tag, Subscriber subscriber, String month) {
        Subscription subscription = mService.getBikeRecord(month)
                .map(new BikeResponseTransformer<List<BikeRecord>>())
                .compose(ApiUtils.applySchedulers())
                .subscribe(subscriber);
        addSubscription(tag, subscription);
    }
}
