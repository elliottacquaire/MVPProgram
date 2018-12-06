package com.li.mvpprogram.search;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.base.SearchToolbarActivity;

/**
 * 应用内搜索
 */

public class SearchActivity extends SearchToolbarActivity {

//    private SearchFragment searchFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, SearchActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search;
    }

    @Override
    protected BaseFragment getFragment() {
        return null;
    }


    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void init() {
        super.init();
//        new SearchPresenter(this,searchFragment);
    }

    @Override
    protected void searchData(String content) {

    }


    @Override
    public void hideSoftKeyboard(boolean flag) {
        super.hideSoftKeyboard(flag);
    }

    @Override
    public void clearFource() {
        super.clearFource();
    }

}
