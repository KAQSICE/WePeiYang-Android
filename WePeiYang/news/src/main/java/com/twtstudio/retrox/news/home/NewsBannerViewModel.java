package com.twtstudio.retrox.news.home;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kelin.mvvmlight.base.ViewModel;
import com.twtstudio.retrox.news.api.HomeNewsBean;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by retrox on 26/02/2017.
 */

public class NewsBannerViewModel implements ViewModel{

    public final ObservableField<BannerData> bannerData = new ObservableField<>();

    public NewsBannerViewModel(HomeNewsBean homeNewsBean) {
        bannerData.set(new BannerData(homeNewsBean));
    }

    @BindingAdapter("bannerData")
    public static void addBanners(BGABanner bgaBanner,BannerData bannerData){
        bgaBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(bgaBanner.getContext())
                        .load(model)

                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });

        bgaBanner.setData(bannerData.getUrls(),bannerData.getTitles());
    }
}
