package com.zl.imovie.view.fragment.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zl.imoivesdk.okhttp.listener.DisposeDataListener;
import com.zl.imovie.R;
import com.zl.imovie.activity.SettingActivity;
import com.zl.imovie.network.http.RequestCenter;
import com.zl.imovie.util.Util;
import com.zl.imovie.view.MyQrCodeDialog;
import com.zl.imovie.view.fragment.BaseFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by zl on 18-3-3.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    /**
     * UI
     */
    private View mContentView;
    private RelativeLayout mLoginLayout;
    private CircleImageView mPhotoView;
    private TextView mLoginInfoView;
    private TextView mLoginView;
    private RelativeLayout mLoginedLayout;
    private TextView mUserNameView;
    private TextView mTickView;
    private TextView mVideoPlayerView;
    private TextView mShareView;
    private TextView mQrCodeView;
    private TextView mUpdateView;

    //自定义了一个广播接收器
    private LoginBroadcastReceiver receiver =
            new LoginBroadcastReceiver();

    public MineFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        registerBroadcast();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_mine_layout, null, false);
        initView();
        return mContentView;
    }


    private void initView() {
        mLoginLayout = (RelativeLayout) mContentView.findViewById(R.id.login_layout);
        mLoginLayout.setOnClickListener(this);
        mLoginedLayout = (RelativeLayout) mContentView.findViewById(R.id.logined_layout);
        mLoginedLayout.setOnClickListener(this);

        mPhotoView = (CircleImageView) mContentView.findViewById(R.id.photo_view);
        mPhotoView.setOnClickListener(this);
        mLoginView = (TextView) mContentView.findViewById(R.id.login_view);
        mLoginView.setOnClickListener(this);
        mVideoPlayerView = (TextView) mContentView.findViewById(R.id.video_setting_view);
        mVideoPlayerView.setOnClickListener(this);
        mShareView = (TextView) mContentView.findViewById(R.id.share_imooc_view);
        mShareView.setOnClickListener(this);
        mQrCodeView = (TextView) mContentView.findViewById(R.id.my_qrcode_view);
        mQrCodeView.setOnClickListener(this);
        mLoginInfoView = (TextView) mContentView.findViewById(R.id.login_info_view);
        mUserNameView = (TextView) mContentView.findViewById(R.id.username_view);
        mTickView = (TextView) mContentView.findViewById(R.id.tick_view);

        mUpdateView = (TextView) mContentView.findViewById(R.id.update_view);
        mUpdateView.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //根据用户信息更新我们的fragment
//        if (UserManager.getInstance().hasLogined()) {
//            if (mLoginedLayout.getVisibility() == View.GONE) {
//                mLoginLayout.setVisibility(View.GONE);
//                mLoginedLayout.setVisibility(View.VISIBLE);
//                mUserNameView.setText(UserManager.getInstance().getUser().data.name);
//                mTickView.setText(UserManager.getInstance().getUser().data.tick);
//            }
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.share_imooc_view:
                //分享Imooc课网址
                shareFriend();
                break;
            case R.id.login_layout:
            case R.id.login_view:
                //未登陆，则跳轉到登陸页面
//                if (!UserManager.getInstance().hasLogined()) {
//                    toLogin();
//                }
                break;
            case R.id.my_qrcode_view:
//                if (!UserManager.getInstance().hasLogined()) {
//                    //未登陆，去登陆。
//                    toLogin();
//                } else {
                    //已登陆根据用户ID生成二维码显示
                    MyQrCodeDialog dialog = new MyQrCodeDialog(mContext);
                    dialog.show();
//                }
                break;
            case R.id.video_setting_view:
                mContext.startActivity(new Intent(mContext, SettingActivity.class));
                break;
            case R.id.update_view:
//                if (hasPermission(Constant.WRITE_READ_EXTERNAL_PERMISSION)) {
//                    checkVersion();
//                } else {
//                    requestPermission(Constant.WRITE_READ_EXTERNAL_CODE, Constant.WRITE_READ_EXTERNAL_PERMISSION);
//                }
                break;
        }
    }

    public void doWriteSDCard() {
        checkVersion();
    }

    /**
     * 去登陆页面
     */
    private void toLogin() {
//        Intent intent = new Intent(mContext, LoginActivity.class);
//        mContext.startActivity(intent);
    }

    /**
     * 分享慕课网给好友
     */
    private void shareFriend() {
//        ShareDialog dialog = new ShareDialog(mContext, false);
//        dialog.setShareType(Platform.SHARE_IMAGE);
//        dialog.setShareTitle("慕课网");
//        dialog.setShareTitleUrl("http://www.imooc.com");
//        dialog.setShareText("慕课网");
//        dialog.setShareSite("imooc");
//        dialog.setShareSiteUrl("http://www.imooc.com");
//        dialog.setImagePhoto(Environment.getExternalStorageDirectory() + "/test2.jpg");
//        dialog.show();
    }

    private void registerBroadcast() {

//        IntentFilter filter =
//                new IntentFilter(LoginActivity.LOGIN_ACTION);
//        LocalBroadcastManager.getInstance(mContext)
//                .registerReceiver(receiver, filter);
    }

    private void unregisterBroadcast() {
        LocalBroadcastManager.getInstance(mContext)
                .unregisterReceiver(receiver);
    }

    //发送版本检查更新请求
    private void checkVersion() {
//        RequestCenter.checkVersion(new DisposeDataListener() {
//            @Override
//            public void onSuccess(Object responseObj) {
//                final UpdateModel updateModel = (UpdateModel) responseObj;
//                if (Util.getVersionCode(mContext) < updateModel.data.currentVersion) {
//                    //说明有新版本,开始下载
//                    CommonDialog dialog = new CommonDialog(mContext, getString(R.string.update_new_version),
//                            getString(R.string.update_title), getString(R.string.update_install),
//                            getString(R.string.cancel), new CommonDialog.DialogClickListener() {
//                        @Override
//                        public void onDialogClick() {
//                            Intent intent = new Intent(mContext, UpdateService.class);
//                            mContext.startService(intent);
//                        }
//                    });
//                    dialog.show();
//                } else {
//                    //弹出一个toast提示当前已经是最新版本等处理
//                }
//            }
//
//            @Override
//            public void onFailure(Object reasonObj) {
//
//            }
//        });
    }

    /**
     * 接收mina发送来的消息，并更新UI
     */
    private class LoginBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (UserManager.getInstance().hasLogined()) {
//                //更新我们的fragment
//                if (mLoginedLayout.getVisibility() == View.GONE) {
//                    mLoginLayout.setVisibility(View.GONE);
//                    mLoginedLayout.setVisibility(View.VISIBLE);
//                    mUserNameView.setText(UserManager.getInstance().getUser().data.name);
//                    mTickView.setText(UserManager.getInstance().getUser().data.tick);
//                    ImageLoaderUtil.getInstance(mContext).displayImage(mPhotoView, UserManager.getInstance().getUser().data.photoUrl);
//                }
//            }
        }
    }
}
