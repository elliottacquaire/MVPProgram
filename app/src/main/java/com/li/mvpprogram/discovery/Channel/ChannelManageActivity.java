package com.li.mvpprogram.discovery.Channel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.base.ToolbarActivity;

public class ChannelManageActivity extends ToolbarActivity {

    private Bundle mBundle;
    private ChannelManageFragment mFragment;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChannelManageActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_common_layout;
    }

    @Override
    protected BaseFragment getFragment() {
        mFragment = ChannelManageFragment.newInstance(mBundle);
        return mFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void handleIntent(Intent intent) {
        super.handleIntent(intent);
        if (intent == null) {
            return;
        }
        mBundle = intent.getBundleExtra(BUNDLE);
    }

    @Override
    protected void init() {
        super.init();
        setTitle(R.string.message_title);
        showBackBtn(true);
        new ChannelManagePresenter(mFragment);
    }

    @Override
    public void onBackPressed() {
//        mFragment.saveChannelListView();
        ChannelManageActivity.this.finish();
    }

    @Override
    protected void onLeftIvClick(View view) {
        super.onLeftIvClick(view);
        onBackPressed();
    }
}
