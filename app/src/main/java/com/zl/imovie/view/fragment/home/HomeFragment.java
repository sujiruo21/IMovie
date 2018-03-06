package com.zl.imovie.view.fragment.home;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zl.imoivesdk.okhttp.CommonOkhttpClient;
import com.zl.imoivesdk.okhttp.listener.DisposeDataListener;
import com.zl.imovie.R;
import com.zl.imovie.adapter.CourseAdapter;
import com.zl.imovie.module.recommand.BaseRecommandModel;
import com.zl.imovie.network.http.RequestCenter;
import com.zl.imovie.view.fragment.BaseFragment;

import static android.content.ContentValues.TAG;

/**
 * Created by zl on 18-3-3.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private View mContentView;
    private TextView mQRCodeView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ListView mListView;
    private ImageView mLoadingView;
    private BaseRecommandModel mRecommendData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();
    }

    private void requestRecommandData() {
        RequestCenter.requestRecommendData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mRecommendData = (BaseRecommandModel)responseObj;
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.e(TAG, "onFailure: " + reasonObj.toString());
            }
        });
    }

    /**
     * 请求成功时执行的方法
     */
    private void showSuccessView() {
        //判断返回数据是否为空
        if(mRecommendData.data.list != null && mRecommendData.data.list.size() > 0){
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mListView.setAdapter(new CourseAdapter(mContext,mRecommendData.data.list));
        }else {
            showErrorView();
        }
    }

    /**
     * 请求失败执行的方法
     */
    private void showErrorView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        mContentView = inflater.inflate(R.layout.fragment_home_layout, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
        mQRCodeView = (TextView) mContentView.findViewById(R.id.qrcode_view);
        mQRCodeView.setOnClickListener(this);
        mCategoryView = (TextView) mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView = (TextView) mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView = (ListView) mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView = (ImageView) mContentView.findViewById(R.id.loading_view);
        AnimationDrawable anim = (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
