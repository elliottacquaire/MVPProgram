package com.li.mvpprogram.patternLock;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.rxpatternlockview.RxPatternLockView;
import com.andrognito.rxpatternlockview.events.PatternLockCompleteEvent;
import com.andrognito.rxpatternlockview.events.PatternLockCompoundEvent;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import io.reactivex.functions.Consumer;

/**
 * A simple {@link Fragment} subclass.
 */
public class PatternLockFragment extends BaseFragment implements PatternLockContract.View {


    @BindView(R.id.head_icon_iv)
    ImageView headIconIv;
    @BindView(R.id.phone_tv)
    TextView phoneTv;
    @BindView(R.id.tip_tv)
    TextView tipTv;
    @BindView(R.id.pattern_lock_view)
    PatternLockView patternLockView;
    @BindView(R.id.switch_tv)
    TextView switchTv;

    private PatternLockContract.Presenter presenter;

    public PatternLockFragment() {
        // Required empty public constructor
    }

    public static PatternLockFragment newInstance() {
        return new PatternLockFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pattern_lock;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            String mFrom = bundle.getString("key");
        }

        initPatternLockView();

    }

    private void initPatternLockView() {
        RxPatternLockView.patternChanges(patternLockView)
                .compose(this.<PatternLockCompoundEvent>bindToLifecycle())
                .subscribe(new Consumer<PatternLockCompoundEvent>() {
                    @Override
                    public void accept(PatternLockCompoundEvent event) throws Exception {
                        if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_STARTED) {
                            Log.d(getClass().getName(), "Pattern drawing started");
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_PROGRESS) {
                            Log.d(getClass().getName(), "Pattern progress: " +
                                    PatternLockUtils.patternToString(patternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_COMPLETE) {
                            Log.d(getClass().getName(), "Pattern complete: " +
                                    PatternLockUtils.patternToString(patternLockView, event.getPattern()));
                        } else if (event.getEventType() == PatternLockCompoundEvent.EventType.PATTERN_CLEARED) {
                            Log.d(getClass().getName(), "Pattern has been cleared");
                        }

                        ///////////////////////


                    }
                });

        RxPatternLockView.patternComplete(patternLockView)
                .compose(this.<PatternLockCompleteEvent>bindToLifecycle())
                .subscribe(new Consumer<PatternLockCompleteEvent>() {
                    @Override
                    public void accept(PatternLockCompleteEvent event) throws Exception {
                        String pwd = PatternLockUtils.patternToString(patternLockView,event.getPattern());
                        if (pwd.length() < 4) {
                            tipTv.setTextColor(Color.RED);
                            tipTv.setText(getString(R.string.please_choose_less_four));
                            patternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                            shakeAnimation();
                            return;
                        }
                    }
                });
    }

    private void shakeAnimation() {
        Animation shake = AnimationUtils.loadAnimation(mActivity, R.anim.shake);
        tipTv.startAnimation(shake);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @OnClick({R.id.head_icon_iv, R.id.switch_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.head_icon_iv:
                break;
            case R.id.switch_tv:
                break;
        }
    }

    @Override
    public void showMsgTip(String msg) {

    }

    @Override
    public void setPresenter(PatternLockContract.Presenter presenter) {
        this.presenter = presenter;
    }

    /**
     * 处理手机号码，中间四位显示*号
     *
     * @param originalPhone
     * @return
     */
    private String handlePhoneNumber(String originalPhone) {
        if (TextUtils.isEmpty(originalPhone)) {
            return originalPhone;
        }
        if (originalPhone.length() != 11) {
            return originalPhone;
        }
        String midFourStr = originalPhone.substring(3, 7);
        return originalPhone.replace(midFourStr, "****");
    }
}
