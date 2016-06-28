package com.yigu.jgj.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brain on 2016/6/26.
 */
public class BaseFrag extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public void showLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).showLoading();
        }
    }

    public void hideLoading() {
        if (getActivity() instanceof BaseActivity) {
            ((BaseActivity) getActivity()).hideLoading();
        }
    }

    public void load() {

    }

}
