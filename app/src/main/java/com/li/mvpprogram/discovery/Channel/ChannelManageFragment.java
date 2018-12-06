package com.li.mvpprogram.discovery.Channel;


import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.li.mvpprogram.R;
import com.li.mvpprogram.base.BaseFragment;
import com.li.mvpprogram.bean.MapVo;
import com.li.mvpprogram.bean.RefreshFromChannelEvent;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.widget.view.DragSortGridView;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 频道管理
 */
public class ChannelManageFragment extends BaseFragment implements ChannelManageContract.View {

    public static final String CHANNELLIST = "CHANNELLIST";

    @BindView(R.id.tv_finish)
    TextView tvFinish;
    @BindView(R.id.dragSort)
    DragSortGridView dragSortGridView;

    private List<MapVo> mChannelList;

    private DragAdapter dragAdapter;

    private ChannelManageContract.Presenter mPresenter;

    public ChannelManageFragment() {
        // Required empty public constructor
    }

    public static ChannelManageFragment newInstance(Bundle bundle) {
        ChannelManageFragment fragment = new ChannelManageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mChannelList = (List<MapVo>)(getArguments().getSerializable(CHANNELLIST));
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_channel_manage;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

        dragAdapter = new ChannelDragAdapter(mChannelList,getActivity());
        dragSortGridView.setAdapter(dragAdapter);
        dragSortGridView.setNumColumns(4);

        dragSortGridView.setNoPositionChangeItemCount(1);

        //设置每行个数
        dragSortGridView.setNumColumns(4);

        //修改长按拖动的响应时间
        dragSortGridView.setDragLongPressTime(1500);

    }

    @Override
    public void setPresenter(ChannelManageContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.tv_finish)
    public void onViewClicked() {
//        mPresenter.saveChannelList(mChannelList);
    }

    @Override
    public void showMsgTip(String msg) {
        if (TextUtils.isEmpty(msg))return;
        ToastUtils.show(mActivity, msg);
    }

    @Override
    public void displayChannelList(Boolean result) {
        showMsgTip("操作成功");
        if (result){
            EventBus.getDefault().post(new RefreshFromChannelEvent(mChannelList));
            getActivity().finish();
        }
    }

    @Override
    public void saveChannelListView() {
        if (mPresenter == null)return;
        mPresenter.saveChannelList(mChannelList);
    }
}
