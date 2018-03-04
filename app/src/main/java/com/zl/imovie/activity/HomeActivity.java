package com.zl.imovie.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zl.imovie.R;
import com.zl.imovie.activity.base.BaseActivity;
import com.zl.imovie.view.fragment.home.HomeFragment;
import com.zl.imovie.view.fragment.home.MessageFragment;
import com.zl.imovie.view.fragment.home.MineFragment;

/**
 * 创建首页所有的Fragment
 */

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout mHomeLayout;
    private RelativeLayout mPondLayout;
    private RelativeLayout mMessageLayout;
    private RelativeLayout mMineLayout;
    private TextView mHomeView;
    private TextView mPondView;
    private TextView mMessageView;
    private TextView mMineView;
    private HomeFragment mHomeFragment;
    private FragmentManager fm;
    private Fragment mMessageFragment;
    private Fragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);

        //实例化页面中所有的控件
        initView();

        //添加默认要显示的Fragment
        mHomeFragment = new HomeFragment();
        fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        //replace=先remove再add
        transaction.replace(R.id.content_layout,mHomeFragment);
        transaction.commit();
    }

    private void initView() {
        mHomeLayout = (RelativeLayout) findViewById(R.id.home_layout_view);
        mHomeLayout.setOnClickListener(this);
        mPondLayout = (RelativeLayout) findViewById(R.id.pond_layout_view);
        mPondLayout.setOnClickListener(this);
        mMessageLayout = (RelativeLayout) findViewById(R.id.message_layout_view);
        mMessageLayout.setOnClickListener(this);
        mMineLayout = (RelativeLayout) findViewById(R.id.mine_layout_view);
        mMineLayout.setOnClickListener(this);

        mHomeView = (TextView) findViewById(R.id.home_image_view);
        mPondView = (TextView) findViewById(R.id.fish_image_view);
        mMessageView = (TextView) findViewById(R.id.message_image_view);
        mMineView = (TextView) findViewById(R.id.mine_image_view);
        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    @Override
    public void onClick(View v) {
        //切换Fragment
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()){
            case R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                //隐藏非当前页的Fragment
                hideFragment(mMessageFragment,transaction);
                hideFragment(mMineFragment,transaction);
                //将我们的HomeFragent显示出来
                if(mHomeFragment == null){
                    mHomeFragment = new HomeFragment();
                    transaction.add(R.id.content_layout,mHomeFragment);
                }else{
                    transaction.show(mHomeFragment);
                }
                break;
            case R.id.message_layout_view:
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);

                //隐藏非当前页的Fragment
                hideFragment(mHomeFragment,transaction);
                hideFragment(mMineFragment,transaction);
                //将我们的MessageFragent显示出来
                if(mMessageFragment == null){
                    mMessageFragment = new MessageFragment();
                    transaction.add(R.id.content_layout,mMessageFragment);
                }else{
                    transaction.show(mMessageFragment);
                }
                break;
            case R.id.mine_layout_view:
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);

                //隐藏非当前页的Fragment
                hideFragment(mHomeFragment,transaction);
                hideFragment(mMessageFragment,transaction);
                //将我们的MessageFragment显示出来
                if(mMineFragment == null){
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.content_layout,mMineFragment);
                }else{
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    /**
     * 用于隐藏非当前页的Fragment
     * @param fragment
     * @param ft
     */
    private void hideFragment(Fragment fragment,FragmentTransaction ft){
        if(fragment != null){
            ft.hide(fragment);
        }
    }
}
