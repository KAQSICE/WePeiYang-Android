package com.twt.service.party.ui.study.detail;

import com.twt.service.party.bean.CourseDetailInfo;

import java.util.List;

/**
 * Created by tjliqy on 2016/8/18.
 */
public interface StudyDetailListView {
    void onGetCourseDetail(List<CourseDetailInfo.DataBean> list);
}
