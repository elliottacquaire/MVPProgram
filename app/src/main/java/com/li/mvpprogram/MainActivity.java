package com.li.mvpprogram;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.base.ToolbarActivity;
import com.li.mvpprogram.config.Constants;
import com.li.mvpprogram.utils.ActivityManagerTool;
import com.li.mvpprogram.utils.ActivityUtils;
import com.li.mvpprogram.utils.PreferencesUtils;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.widget.dialog.DialogManager;
import org.greenrobot.eventbus.EventBus;

/**
 * 首页
 * 包含四个选项卡:乡邻，消息，头条,我的
 * 微博
 */

public class MainActivity extends ToolbarActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    /**
     * 乡邻选项卡
     */
    public static final String HOME_TAB = "homeTab";
    /**
     * 微博选项卡
     */
    public static final String MESSAGE_TAB = "messageTab";
    /**
     * 头条选项卡
     */
    public static final String HEADLINE_TAB = "headlineTab";
    /**
     * 我的选项卡
     */
    public static final String MINE_TAB = "mineTab";

    public static final String FROM_MAIN = "MainActivity";

    @BindView(R.id.home_tab)
    RadioButton homeTab;
    @BindView(R.id.headline_tab)
    RadioButton headlineTab;
    @BindView(R.id.message_tab)
    RadioButton messageTab;
    @BindView(R.id.mine_tab)
    RadioButton mineTab;
    @BindView(R.id.rg_bottom)
    RadioGroup rgBottom;
    @BindView(R.id.fl_unread_amonunt_dot_main)
    FrameLayout mDotLayout;//消息小红点
    @BindView(R.id.rc_unread_tip_main)
    TextView mMessageAmount;//消息数
    @BindView(R.id.publish_weibo)
    RadioButton publishWeibo;

    private Unbinder unbinder;

    private HomeFragment mHomeFragment;
//    private ConversationListFragmentEx mMessageFragment;
    private MicroBlogHomeFragment mMicroBlogFragment;
    private HeadlineFragment mHeadlineFragment;

    private MineFragment mMineFragment;

    public static boolean mIsForeground = false;
    public static final int REQUEST_CODE_DYNAMIC = 0x13;
    public static final String CLASS_NAME = "com.xianglin.app.biz.gold.service.StepService";
    private String currentTab = HOME_TAB;

    /**
     * 用于页面跳转传值
     *
     * @param context
     * @param bundle
     * @return
     */
    public static Intent makeIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(BUNDLE, bundle);
        return intent;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
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
    protected void onDestroy() {
        DialogManager.hideDialog();
        mIsForeground = false;
        super.onDestroy();
        unbinder.unbind();
//        EventBus.getDefault().removeStickyEvent(NoticeWebDataEven.class);
        EventBus.getDefault().unregister(this);
        //停止服务
        //AppUtils.stopService(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData(savedInstanceState);
        PreferencesUtils.putString(Constants.UPDATE_PREFERENCE, this, Constants.UPDATE_KEY_EXTRAS_IS_LOWDING, Constants.DEFAULT_DID);
        //启动服务
//        if (!UserInfoManager.getInstance().getUserType().equalsIgnoreCase(Constant.UserType.visitor.name())) {
//            LogUtils.d("启动服务");
//            AppUtils.setupService(this);
//        }
    }


//    //操作消息小红点
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessage(IsUnReadXYMessage event) {
//        if (event != null) {
//            if (event.isHave()) {
//                if (mDotLayout != null) {
//                    mDotLayout.setVisibility(View.VISIBLE);
//                }
//                if (mMessageAmount != null && !TextUtils.isEmpty(event.getAllAmount())) {
//                    mMessageAmount.setText(event.getAllAmount());
//                }
//            } else {
//                if (mDotLayout != null) {
//                    mDotLayout.setVisibility(View.GONE);
//                }
//            }
//
//        }
//    }

//    /*推送通知触发接口*/
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onNotice(NoticeWebDataEven even) {
//        if (even != null) {
//            EventBus.getDefault().removeStickyEvent(NoticeWebDataEven.class);
//            Bundle bundle = new Bundle();
//            bundle.putString(WebViewActivity.URL, even.getUrl());
//            //startActivity(WebViewActivity.makeIntent(this, bundle));
//            ActivityUtils.startWebActivity(this, bundle, even.getUrl());
//        }
//    }

    /*发表成功*/
    /*
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCirclePushSuccess(CirclePushSuccess even) {
        if (even != null) {
            if (!getTitle().equals(getString(R.string.message_title))) {
                checkTab(MESSAGE_TAB);
            }
        }
    }
    */

    /*推送通知触发接口*/
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void onStartActivity(NoticeActivityDataEven even) {
//        if (even != null) {
//            EventBus.getDefault().removeStickyEvent(NoticeActivityDataEven.class);
//            if (even.getCls() != null) {
//                Intent mIntent = new Intent(this, even.getCls());
//                startActivity(mIntent);
//            }
//        }
//    }



    /*重新登录后 用户身份切换回调*/
   /* @Subscribe(threadMode = ThreadMode.MAIN, sticky = false)
    public void onStartChatActivity(UserSwitcherDataEven even) {
        if (even != null) {

            getSupportFragmentManager()
                    .beginTransaction()
                    .remove(mHomeFragment)
                    .remove(mMineFragment)
                    .add(getFragmentContentId(), HomeFragment.newInstance(), HOME_TAB)
                    .add(getFragmentContentId(), MineFragment.newInstance(), MINE_TAB)
                    .commitNowAllowingStateLoss();
            mHomeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(MainActivity.HOME_TAB);
            mMineFragment = (MineFragment) getSupportFragmentManager().findFragmentByTag(MainActivity.MINE_TAB);
            getSupportFragmentManager()
                    .beginTransaction()
                    .show(mHomeFragment)
                    .hide(mMicroBlogFragment)
                    .hide(mHeadlineFragment)
                    .hide(mMineFragment)
                    .commitAllowingStateLoss();
            checkTab(HOME_TAB);

        }
    }*/

    private void initData(Bundle savedInstanceState) {
        initTab(savedInstanceState);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("current_tab", currentTab);
    }

    public void initTab(Bundle savedInstanceState) {

        unbinder = ButterKnife.bind(this);

        mHomeFragment = HomeFragment.newInstance();
//        mMessageFragment = new ConversationListFragmentEx();
        mMicroBlogFragment = MicroBlogHomeFragment.newInstance();

        mHeadlineFragment = HeadlineFragment.newInstance();
        mMineFragment = MineFragment.newInstance();

        if (savedInstanceState == null) { // 如果没有有缓存,添加新的fragment, 如果有缓存从缓存读取,此操作已经在switchFragmentInMainPage中处理
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (transaction == null) {
                return;
            }

            Fragment homeFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.HOME_TAB);
            Fragment messageFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.MESSAGE_TAB);
            Fragment discoveryFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.HEADLINE_TAB);
            Fragment mineFragment = getSupportFragmentManager().findFragmentByTag(MainActivity.MINE_TAB);
            if (homeFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mHomeFragment,
                        HOME_TAB
                );
            }

            if (messageFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mMicroBlogFragment,
                        MESSAGE_TAB
                ).hide(mMicroBlogFragment);
            }

            if (discoveryFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mHeadlineFragment,
                        HEADLINE_TAB
                ).hide(mHeadlineFragment);
            }

            if (mineFragment == null) {
                transaction.add(
                        getFragmentContentId(),
                        mMineFragment,
                        MINE_TAB
                ).hide(mMineFragment);
            }
            transaction.commitAllowingStateLoss();

        } else {

            checkTab(savedInstanceState.getString("current_tab"));
        }

        rgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                {
                    switch (checkedId) {
                        case R.id.home_tab:

                            showToolbar(false);
                            String defaultVillage = "";
                            if (null != mHomeFragment) {
//                        defaultVillage = mHomeFragment.getDefaultVillageName();
                            }
                            if (!TextUtils.isEmpty(defaultVillage)) {
                                setTitle(defaultVillage);
                            } else {
                                setTitle(getString(R.string.home_title));
                            }
                            showRightIv(true);
                            showLeftIv(true);
                            showRight2Iv(false);
                            initToolBarColorWithTransparent();
//                    if (mHomeFragment.isMerchantUser()) {
//                        setLeftIv(R.drawable.scan_color);
//                    } else {
//                        setLeftIv(R.drawable.ic_toolbar_left_camera);
//                    }
                            setRightIv(R.mipmap.ic_add);
                            restoretToolBarColorWithAlpha();
//                    if (mHomeFragment.isVisible()) {
//                        return;
//                    }
                            changeTab(HOME_TAB);
                            break;

                        case R.id.message_tab:
//                    UMAgentUtils.onEvent(this, getString(R.string.info_tab));
                            showToolbar(false);
//                    setTitle(getString(R.string.message_title));
                            showRightIv(true);
                            setRightIv(R.mipmap.ic_add);
                            showLeftIv(false);
                            initToolBarColorWithAlpha();
//                    if (mMicroBlogFragment.isVisible()) {
//                        return;
//                    }
                            changeTab(MESSAGE_TAB);
                            break;
                        case R.id.headline_tab:
//                    UMAgentUtils.onEvent(this, getString(R.string.toutiao_tab));
                            showToolbar(true);
                            setTitle(getString(R.string.headline_tab));
                            showRightIv(false);
                            showRight2Iv(false);
//                    setLeftIv(R.drawable.back);
                            showLeftIv(false);
                            initToolBarColorWithAlpha();
//                    if (mHeadlineFragment.isVisible() && mHeadlineFragment != null && !mHeadlineFragment.isShow()) {
//                        showLeftIv(true);
//                        return;
//                    }
                            changeTab(HEADLINE_TAB);
                            break;
                        case R.id.mine_tab:

                            showToolbar(false);
                            setTitle(getString(R.string.mine_title));
                            showRightIv(false);
                            showRight2Iv(false);
                            showLeftIv(false);
                            initToolBarColorWithTransparent();
//                    if (mMineFragment.isVisible()) {
//                        return;
//                    }
                            changeTab(MINE_TAB);
                            break;
                        //添加发布微博入口
                        case R.id.publish_weibo:
                            //发布乡邻微博

                            //从首页发布微博清空组织名字
//                    UserInfoManager.getInstance().setGroupName("");
//                    Bundle bundle = new Bundle();
//                    bundle.putInt(CirclePublishActivity.CIRCLE_TYPE_EXTRAS, CircleFragFactory.CIRCLE_PUBLISH_ARTICLE);
//                    bundle.putString(ORGINID, affairsGroupId + "");
//                    UserInfoManager.getInstance().setOrganizationId(new Long[]{0L});
//                    startActivity(CirclePublishActivity.makeIntent(this, bundle));

                            break;

                    }
                    refreshMessageUnaRead();
                }
            }
        });

        headlineTab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //头条tab点击刷新
//                        if (mHeadlineFragment.isVisible()) {
//                            EventBus.getDefault().post(new HeadLineDataEvent());
//                        }
                        break;
                }
                return false;
            }
        });
        checkTab(HOME_TAB);//初始化选中乡邻
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /**
     * 点击底部RadioButton切换选项卡
     *
     * @param tab
     */
    private void changeTab(String tab) {
        currentTab = tab;
        ActivityUtils.switchFragmentInMainPage(
                getSupportFragmentManager(),
                tab
        );
    }

    /**
     * 非点击底部RadioButton切换选项卡
     *
     * @param tab
     */
    public void checkTab(String tab) {
        if (TextUtils.isEmpty(tab)) return;
        switch (tab) {
            case HOME_TAB:
                if (homeTab == null) return;
                homeTab.setChecked(true);
                break;
            case HEADLINE_TAB:
                if (headlineTab == null) return;
                headlineTab.setChecked(true);
                break;
            case MESSAGE_TAB:
                if (messageTab == null) return;
                messageTab.setChecked(true);
                break;
            case MINE_TAB:
                if (mineTab == null) return;
                mineTab.setChecked(true);
                break;
        }

    }

    private boolean mIsExit; // 是否退出App

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mIsExit) {
                //关闭计步服务

                //清理融云token key
//                PreferencesUtils.putString(XLApplication.getInstance(), Constants.RONG_YUN_TOKEN, "");
//                PollingUtils.stopPollingService(XLApplication.getInstance());
                ActivityManagerTool.getActivityManager().exit();
            } else {

                if (headlineTab.isChecked() && mHeadlineFragment != null && !mHeadlineFragment.isShow()) { //发现页关闭状态先返回再退出
                    mHeadlineFragment.onPagerOpened();
                } else {
                    ToastUtils.show(this, getString(R.string.exitapp), Toast.LENGTH_SHORT);
                    mIsExit = true;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mIsExit = false;
                        }
                    },2000);
                }

            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        if (mIsForeground) {
            //未登录状态下，点击消息tab到登录页面，返回后恢复选中homeTab
            checkTab(currentTab);
        }
        mIsForeground = true;
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        initData(null);
        setIntent(intent);
        super.onNewIntent(intent);
    }

    @Override
    protected void onRightIvClick(View view) {
        super.onRightIvClick(view);
        if (homeTab.isChecked() || messageTab.isChecked() || headlineTab.isChecked()) {

            //首页、消息、头条tab右上角十字按钮统一弹框
        } else if (MINE_TAB.equals(view.getTag())) {
            //广场和我的界面都跳转 我的乡音
            if (MINE_TAB.equals(view.getTag())) {

            }

        }

    }

    @Override
    protected void onRight2IvClick(View view) {
        super.onRight2IvClick(view);
        if (MESSAGE_TAB.equals(view.getTag())) {

        }

    }

    @Override
    protected void onLeftIvClick(View view) {
        super.onLeftIvClick(view);
        //发布乡邻微博
        if (homeTab.isChecked()) {


        } else if (headlineTab.isChecked()) {
            if (mHeadlineFragment == null) return;
//            mHeadlineFragment.onPagerOpened();
        }
    }


    private void refreshMessageUnaRead() {

    }

//    public MicroBlogHomeFragment getmMicroBlogFragment() {
//        return mMicroBlogFragment;
//    }


}
