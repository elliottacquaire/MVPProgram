/**/

package com.li.mvpprogram.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.*;
import com.li.mvpprogram.MainActivity;
import com.li.mvpprogram.R;
import com.li.mvpprogram.config.AppContants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 */
public class ActivityUtils {

    /**
     * 将fragment添加到容器中,由fragmentManager执行
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void addFragmenttoActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId,
            String tag
    ) {
        if (fragmentManager == null || fragment == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment, tag);
//        transaction.commit();
        transaction.commitNowAllowingStateLoss();
    }

    public static void addFragmenttoActivity(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId
    ) {
        addFragmenttoActivity(
                fragmentManager,
                fragment,
                frameId,
                null
        );
    }


    /**
     * 替换fragment
     *
     * @param fragmentManager
     * @param fragment
     * @param frameId
     */
    public static void replaceFragment(
            @NonNull FragmentManager fragmentManager,
            @NonNull Fragment fragment,
            int frameId
    ) {
        if (fragment == null || fragmentManager == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
//        transaction.commit();
        transaction.commitNowAllowingStateLoss();
    }

    /**
     * 切换首页选项卡的显示
     *
     * @param fragmentManager
     * @param fragmentTag
     */
    public static void switchFragmentInMainPage(
            @NonNull FragmentManager fragmentManager,
            @NonNull String fragmentTag
    ) {
        if (fragmentManager == null || fragmentTag == null) {
            return;
        }
        Fragment homeFragment = fragmentManager.findFragmentByTag(MainActivity.HOME_TAB);
        Fragment messageFragment = fragmentManager.findFragmentByTag(MainActivity.MESSAGE_TAB);
        Fragment headlineFragment = fragmentManager.findFragmentByTag(MainActivity.HEADLINE_TAB);
        Fragment mineFragment = fragmentManager.findFragmentByTag(MainActivity.MINE_TAB);
        if (homeFragment == null || messageFragment == null || headlineFragment == null || mineFragment == null) {
            return;
        }
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (transaction == null) {
            return;
        }
        switch (fragmentTag) {
            case MainActivity.HOME_TAB: {
                transaction.hide(messageFragment);
                transaction.hide(headlineFragment);
                transaction.hide(mineFragment);
                transaction.show(homeFragment);
                transaction.commitNowAllowingStateLoss();
//                transaction.commit();
            }
            break;
            case MainActivity.MESSAGE_TAB: {
                transaction.hide(homeFragment);
                transaction.hide(headlineFragment);
                transaction.hide(mineFragment);
                transaction.show(messageFragment);
                transaction.commitNowAllowingStateLoss();
//                transaction.commit();
            }
            break;
            case MainActivity.HEADLINE_TAB: {
                transaction.hide(homeFragment);
                transaction.hide(messageFragment);
                transaction.hide(mineFragment);
                transaction.show(headlineFragment);
                transaction.commitNowAllowingStateLoss();
//                transaction.commit();
            }
            break;
            case MainActivity.MINE_TAB: {
                transaction.hide(homeFragment);
                transaction.hide(messageFragment);
                transaction.hide(headlineFragment);
                transaction.show(mineFragment);
                transaction.commitNowAllowingStateLoss();
//                transaction.commit();
            }
            break;
        }

    }




    public static void startActivity(Context context, Intent intent) {
        if (null != context) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.translate_fade_in, R.anim.translate_fade_out);
            ActivityCompat.startActivity(context, intent, compat.toBundle());
        }
    }

    public static void startActivityForResult(Activity context, Intent intent, int reuestCode) {
        if (null != context) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.translate_fade_in, R.anim.translate_fade_out);
            ActivityCompat.startActivityForResult(context, intent, reuestCode, compat.toBundle());
        }
    }

    /**
     * 跳转至WebView  x5和原生内核在此处分发
     *
     * @param context
     * @param bundle
     * @param url
     */
    public static void startWebActivity(Context context, Bundle bundle, String url) {
        if (context == null) {
            throw new NullPointerException("context is null");
        }
        if (bundle == null) {
            throw new NullPointerException("bundle params is null");
        }
        if (StringUtils.isEmpty(url)) {
            throw new NullPointerException("url is null");
        }
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.translate_fade_in, R.anim.translate_fade_out);
        //分发至不同容器
        Pattern pattern = Pattern.compile(AppContants.GAME_URL_REGEX);
        Matcher m = pattern.matcher(url);
        if (m.matches()) {
            //分发至x5内核浏览器
//            ActivityCompat.startActivity(context, com.xianglin.app.widget.x5webview.X5WebViewActivity.makeIntent(context, bundle), compat.toBundle());
        } else {
            //分发至原生内核浏览器
//            ActivityCompat.startActivity(context, com.xianglin.app.widget.webview.WebViewActivity.makeIntent(context, bundle), compat.toBundle());
        }
    }
}
