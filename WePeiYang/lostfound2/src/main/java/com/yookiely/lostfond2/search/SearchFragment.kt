package com.yookiely.lostfond2.search

import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.lostfond2.R
import com.yookiely.lostfond2.service.MyListDataOrSearchBean
import com.yookiely.lostfond2.service.Utils
import com.yookiely.lostfond2.waterfall.WaterfallTableAdapter
import kotlinx.android.synthetic.main.lf_fragment_waterfall.*

class SearchFragment : Fragment(), SearchContract.SearchUIView {
    private lateinit var tableAdapter: WaterfallTableAdapter
    private val searchLayoutManager = GridLayoutManager(activity, 2)
    private var isLoading = false
    private var isRefresh = false
    private var beanList = ArrayList<MyListDataOrSearchBean>()
    var lostOrFound = "lost"
    var page = 1
    var time = Utils.ALL_TIME
    private var isSubmit = false
    private var keyword: String? = null//搜索关键字

    private val searchPresenter = SearchPresenterImpl(this)

    companion object {
        fun newInstance(type: String): SearchFragment {
            val args = Bundle()
            args.putString("index", type)
            val fragment = SearchFragment()
            fragment.arguments = args

            return fragment
        }
    }

    fun setKeyword(keyword: String) {
        this.keyword = keyword
        isSubmit = true
        loadWaterfallDataWithTime(time)
    }

    fun setTimeAndLoad(time: Int) {
        this.time = time
        if (isSubmit && keyword != null) {
            loadWaterfallDataWithTime(this.time)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.lf_fragment_waterfall, container, false)
        val searchRefresh = view.findViewById<SwipeRefreshLayout>(R.id.waterfall_refresh)
        val searchRecyclerView = view.findViewById<RecyclerView>(R.id.waterfall_recyclerView)
        val searchNoRes = view.findViewById<LinearLayout>(R.id.waterfall_no_res)

        searchRecyclerView.layoutManager = searchLayoutManager
        searchNoRes.visibility = View.GONE
        val bundle = arguments
        lostOrFound = bundle!!.getString("index")
        tableAdapter = WaterfallTableAdapter(beanList, this.activity!!, lostOrFound)
        searchRecyclerView.adapter = tableAdapter
        searchRefresh.setOnRefreshListener(this::refresh)

        searchRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val totalCount = searchLayoutManager.itemCount
                val lastPositions = searchLayoutManager.findLastCompletelyVisibleItemPosition()

                if (!isLoading && (totalCount == lastPositions + 1)) {
                    page++
                    isLoading = true

                    searchPresenter.apply {
                        if (time == 4) {
                            loadWaterfallData(lostOrFound, keyword!!, page, time)
                        } else {
                            loadWaterfallDataWithTime(lostOrFound, keyword!!, page, time)
                        }
                    }
                }
            }
        })

        return view
    }

    private fun refresh() {
        isLoading = true
        isRefresh = true
        page = 1
        searchPresenter.loadWaterfallDataWithTime(lostOrFound, keyword!!, page, this.time)
    }

    override fun setSearchData(waterfallBean: List<MyListDataOrSearchBean>) {
        waterfall_no_res.apply {
            visibility = if (waterfallBean.isEmpty() && page == 1) View.VISIBLE else View.GONE

            if (isRefresh) {
                beanList.clear()
            }
            val dataBean: MutableList<MyListDataOrSearchBean> = mutableListOf()

            for (i in waterfallBean) {
                //1是找到
                if (lostOrFound == "found" && i.type == 1) {
                    dataBean.add(i)
                } else {
                    if (i.type == 0 && lostOrFound == "lost") {
                        dataBean.add(i)
                    }
                }
            }
            visibility = if (dataBean.size == 0 && page == 1) View.VISIBLE else View.GONE

            beanList.addAll(dataBean)
            tableAdapter.notifyDataSetChanged()
            waterfall_refresh.isRefreshing = false
            isLoading = false
            isRefresh = false
        }
    }

    override fun loadWaterfallDataWithTime(time: Int) {
        this.time = time
        page = 1
        isRefresh = true
        searchPresenter.loadWaterfallDataWithTime(lostOrFound, keyword!!, page, this.time)
    }
}