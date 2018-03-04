package com.zl.imovie.view.fragment.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zl.imovie.R;
import com.zl.imovie.view.fragment.BaseFragment;

/**
 * Created by zl on 18-3-3.
 */

public class MessageFragment extends BaseFragment {

    private View mContentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_message_layout, container, false);
        return mContentView;
    }
}
