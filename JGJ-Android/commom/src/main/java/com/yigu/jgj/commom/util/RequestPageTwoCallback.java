package com.yigu.jgj.commom.util;

/**
 * Created by brain on 2016/7/25.
 */
public interface RequestPageTwoCallback<T> {
    void success(Integer isNext,Integer countld,Integer countone,Integer counttwo,T success);
}
