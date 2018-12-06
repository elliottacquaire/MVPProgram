package com.li.mvpprogram.patternLock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.base.ToolbarActivity;

public class PatternLockActivity extends ToolbarActivity {

    private PatternLockFragment patternLockFragment;


    private Bundle mBundle;

    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PatternLockActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_common_layout;
    }

    @Override
    protected BaseFragment getFragment() {
        patternLockFragment = PatternLockFragment.newInstance();
        patternLockFragment.setArguments(mBundle);
        return patternLockFragment;
    }

    @Override
    protected int getFragmentContentId() {
        return R.id.contentFrame;
    }

    @Override
    protected void init() {
        super.init();
        setTitle("shoushimima");
        showBackBtn(true);

        new PatternLockPresenter(patternLockFragment);
    }

    @Override
    protected void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }
        mBundle = intent.getBundleExtra(BUNDLE);
    }

    @Override
    protected void onLeftIvClick(View view) {
        super.onLeftIvClick(view);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
