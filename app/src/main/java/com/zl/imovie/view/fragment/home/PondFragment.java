package com.zl.imovie.view.fragment.home;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.imovie.R;
import com.zl.imovie.view.fragment.BaseFragment;

/**
 * @author zl
 * @function
 */

public class PondFragment extends BaseFragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_pond_layout,container,false);
        return mContentView;
    }
}
