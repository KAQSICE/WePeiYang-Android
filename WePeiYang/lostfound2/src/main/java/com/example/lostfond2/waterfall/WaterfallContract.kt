package com.example.lostfond2.waterfall

import com.example.lostfond2.service.Data
import com.example.lostfond2.service.MyListDataOrSearchBean

interface WaterfallContract {

    interface WaterfallView {
        fun setWaterfallData(waterfallBean: List<MyListDataOrSearchBean>)

        fun loadWaterfallDataWithType(type: Int)
    }

    interface WaterfallPresenter {
        fun setWaterfallData(waterfallBean: List<MyListDataOrSearchBean>)

        fun loadWaterfallData(lostOrFound: String, page: Int)

        fun loadWaterfallDataWithType(lostOrFound: String, page: Int, type: Int)
    }
}