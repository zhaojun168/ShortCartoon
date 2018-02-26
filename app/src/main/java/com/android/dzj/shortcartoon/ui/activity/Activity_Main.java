package com.android.dzj.shortcartoon.ui.activity;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;

import com.android.dzj.shortcartoon.R;
import com.android.ui.activity.BaseActivity;
import com.android.util.UiUtils;

/**
 * Created by Administrator on 2018/2/24.
 */

public class Activity_Main extends BaseActivity {
    private Context mContext;
    private Activity_Cartoon mCartoon;// 漫画
    private Activity_Two mTwo;// 等待1
    private Activity_Three mThree;// 等待2
    private RadioButton mRa_cartoon, mRa_two, mRa_three;
    private FragmentManager manager;
    private long exitTime = 0;

    @Override
    protected void setRootView() {
//        MobclickAgent.openActivityDurationTrack(false);
        setContentView(R.layout.activity_main);
        mContext = this;
    }

    @Override
    protected void initWidget() {
        manager = this.getSupportFragmentManager();
        selectionFragment(1);
        mRa_cartoon = (RadioButton) findViewById(R.id.cartoon);
        mRa_two = (RadioButton) findViewById(R.id.two);
        mRa_three = (RadioButton) findViewById(R.id.three);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void setListeners() {
        mRa_cartoon.setOnClickListener(this);
        mRa_two.setOnClickListener(this);
        mRa_three.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int fragment = 0;
        switch (v.getId()) {
            case R.id.cartoon:
                fragment = 1;
                break;
            case R.id.two:
                fragment = 2;
                break;
            case R.id.three:
                fragment = 3;
                break;
        }
        if (fragment > 0) {
            selectionFragment(fragment);
        }
    }

    private void selectionFragment(int id) {
        FragmentTransaction transaction = manager.beginTransaction();// 开启Fragment事务
        hideFragments(transaction);// 每次显示Fragment前，先隐藏其它Fragment
        switch (id) {
            case 1:
                if (mCartoon == null) {
                    mCartoon = new Activity_Cartoon();// 如果为空，就实例化
                    transaction.add(R.id.flayout, mCartoon);// 并且加入集合
                } else {
                    transaction.show(mCartoon);// 否则显示fragment
                }
                break;
            case 2:
                if (mTwo == null) {
                    mTwo = new Activity_Two();
                    transaction.add(R.id.flayout, mTwo);
                } else {
                    transaction.show(mTwo);
                }
                break;
            case 3:
                if (mThree == null) {
                    mThree = new Activity_Three();
                    transaction.add(R.id.flayout, mThree);
                } else {
                    transaction.show(mThree);
                }

                break;
        }
        transaction.commit();// 提交事务，显示Fragment
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (mCartoon != null)
            transaction.hide(mCartoon);
        if (mTwo != null)
            transaction.hide(mTwo);
        if (mThree != null)
            transaction.hide(mThree);
    }

    /**
     * 再按一次退出
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                UiUtils.toast(mContext, "再按一次退出");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onRelease() {
    }

    @Override
    protected void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }
}
