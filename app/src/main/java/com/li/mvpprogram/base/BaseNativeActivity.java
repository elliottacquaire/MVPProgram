/**/

package com.li.mvpprogram.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import com.li.mvpprogram.utils.ActivityUtils;

/**
 * 原生页面Activity基类
 * Activity基类<br/>
 * 无toolbar<br/>
 */
public abstract class BaseNativeActivity extends BaseActivity {

    /**
     * 页面间传值key常量
     */
    public static final String BUNDLE = "bundle";

    /**
     * 布局文件Id
     *
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 获取fragment
     *
     * @return
     */
    protected abstract BaseFragment getFragment();

    /**
     * 布局中Fragment的ID
     *
     * @return
     */
    protected abstract int getFragmentContentId();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initStatusBar();
        setContentView(getContentViewId());
        // 处理传过来的intent
        handleIntent(getIntent());
        // 将fragment添加到activity
        addFragmentToActivity();
        // 后续初始化操作
        init();
    }

    private void addFragmentToActivity() {
        //android-support-v4.jar
        // with fragments is done through FragmentManager, which can be obtained via Activity.getFragmentManager() and Fragment.getFragmentManager().
        Fragment fragment = getSupportFragmentManager().findFragmentById(getFragmentContentId());
        if (fragment == null) {
            // create the fragment
            fragment = getFragment();
            if (fragment != null) {
                ActivityUtils.addFragmenttoActivity(
                        getSupportFragmentManager(),
                        fragment,
                        getFragmentContentId()
                );
            }


        }
    }

    public void replaceFragmentToActivity() {
        Fragment fragment = getFragment();
        if (fragment != null) {
            ActivityUtils.replaceFragment(
                    getSupportFragmentManager(),
                    fragment,
                    getFragmentContentId()
            );
        }
    }

    /**
     * 获取Intent
     *
     * @param intent
     */
    protected void handleIntent(Intent intent) {
    }

    /**
     * 加载完fragment之后进行一些初始化操作
     */
    protected void init() {
    }


    /**
     * 初始化系统状态栏
     * 在4.4以上版本是否设置透明状态栏
     */
    private void initStatusBar() {
        if (!setTranslucentStatusBar()) {
            return;
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }

    /**
     * 设置透明状态栏
     *
     * @return true 设置, false 不设置, 默认为false
     */
    protected boolean setTranslucentStatusBar() {
        return false;
    }


}
