package com.li.mvpprogram.discovery.recommend;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.li.mvpprogram.R;

import java.util.List;

/**
 *
 */

public class RecommendAdapter extends BaseMultiItemQuickAdapter<MsgVoMuti, BaseViewHolder> {

    private Context context;
    private Fragment mFragment;
    public RecommendAdapter(Context context, Fragment fragment, List data) {
        super(data);
        this.context = context;
        this.mFragment = fragment;
        addItemType(MsgVoMuti.SINGLEIMG, R.layout.item_headline_news);
        addItemType(MsgVoMuti.VIDEO, R.layout.item_headline_threepic);
        addItemType(MsgVoMuti.THREEIMG, R.layout.item_headline_threepic);
        addItemType(MsgVoMuti.TEXTTIPS, R.layout.item_headline_tips);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgVoMuti item) {
        if (helper == null || item == null) {
            return;
        }
        switch (helper.getItemViewType()) {
            case MsgVoMuti.TEXTTIPS:
                helper.setText(R.id.tv_refresh_num, "上次您看到这里  点击刷新");
                break;
            case MsgVoMuti.SINGLEIMG:
                //标题
//                if (TextUtils.isEmpty(item.getMsgVo().getMsgTitle())) {
//                    helper.setText(R.id.tv_news_title, "暂无数据");
//                } else {
//                    helper.setText(R.id.tv_news_title, StringUtils.replaceBlank(item.getMsgVo().getMsgTitle()));
//                }
                //已阅 && 未阅
                if (item.isRead()){
                    helper.setTextColor(R.id.tv_news_title, ContextCompat.getColor(context,R.color.text999));
                }else {
                    helper.setTextColor(R.id.tv_news_title, ContextCompat.getColor(context,R.color.textColorBackPrimary));
                }
                //时间
//                String time = item.getMsgVo().getDateTime();
//                if (TextUtils.isEmpty(time)) {
//                    helper.setText(R.id.tv_news_time, "暂无数据");
//                } else {
//                    helper.setText(R.id.tv_news_time, time);
//                }
                //来源
//                if (TextUtils.isEmpty(item.getMsgVo().getMsgSource())) {
//                    helper.setText(R.id.tv_news_source, "暂无数据");
//                } else {
//                    helper.setText(R.id.tv_news_source, item.getMsgVo().getMsgSource());
//                }
//                //阅读数
//                if (item.getMsgVo().getReadNum() == 0) {
//                    helper.getView(R.id.tv_news_num).setVisibility(View.GONE);
//                    helper.setText(R.id.tv_news_num, "暂无数据");
//                } else {
//                    helper.getView(R.id.tv_news_num).setVisibility(View.VISIBLE);
//                    helper.setText(R.id.tv_news_num, item.getMsgVo().getReadNum()+"阅读");
//                }
//                //图片
//                ImageView iv_news = (ImageView) helper.getView(R.id.iv_news);
//                if (StringUtils.isEmpty(item.getMsgVo().getTitleImgList())) {
//                    helper.getView(R.id.iv_news).setVisibility(View.GONE);
//                    if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                        Glide.with(mFragment).load(item.getMsgVo().getTitleImg()).placeholder(R.drawable.news_default_bg).into(iv_news);
//                    } else {
//                        iv_news.setAdjustViewBounds(true);
//                        Glide.with(mFragment).load(item.getMsgVo().getTitleImg()).placeholder(R.drawable.news_default_bg)
//                                .crossFade().centerCrop().into(iv_news);
//                    }
//                } else {
//                    helper.getView(R.id.iv_news).setVisibility(View.VISIBLE);
//                    String[] imgUrl = item.getMsgVo().getTitleImgList().split(",");
//                    if (imgUrl.length > 0 && imgUrl.length < 3) {
//                        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                            Glide.with(mFragment).load(imgUrl[0]).placeholder(R.drawable.news_default_bg).into(iv_news);
//                        } else {
//                            iv_news.setAdjustViewBounds(true);
//                            Glide.with(mFragment).load(imgUrl[0]).placeholder(R.drawable.news_default_bg)
//                                    .crossFade().centerCrop().into(iv_news);
//                        }
//                    }
//
//                }

                break;
            case MsgVoMuti.THREEIMG:
                //标题
//                if (TextUtils.isEmpty(item.getMsgVo().getMsgTitle())) {
//                    helper.setText(R.id.tv_news_title, "暂无数据");
//                } else {
//                    helper.setText(R.id.tv_news_title, StringUtils.replaceBlank(item.getMsgVo().getMsgTitle()));
//                }
//                if (item.isRead()){
//                    helper.setTextColor(R.id.tv_news_title, ContextCompat.getColor(context,R.color.text999));
//                }else {
//                    helper.setTextColor(R.id.tv_news_title, ContextCompat.getColor(context,R.color.textColorBackPrimary));
//                }
//                //时间
//                String timeT = item.getMsgVo().getDateTime();
//                if (TextUtils.isEmpty(timeT)) {
//                    helper.setText(R.id.tv_news_time, "暂无数据");
//                } else {
//                    helper.setText(R.id.tv_news_time, timeT);
//                }
//                //来源
//                if (TextUtils.isEmpty(item.getMsgVo().getMsgSource())) {
//                    helper.setText(R.id.tv_news_source, "暂无数据");
//                } else {
//                    helper.getView(R.id.tv_news_num).setVisibility(View.VISIBLE);
//                    helper.setText(R.id.tv_news_source, item.getMsgVo().getMsgSource());
//                }
//                //阅读数
//                if (item.getMsgVo().getReadNum() == 0) {
//                    helper.getView(R.id.tv_news_num).setVisibility(View.GONE);
//                    helper.setText(R.id.tv_news_num, "暂无数据");
//                } else {
//                    helper.getView(R.id.tv_news_num).setVisibility(View.VISIBLE);
//                    helper.setText(R.id.tv_news_num, item.getMsgVo().getReadNum()+"阅读");
//                }
//                //图片
//                ImageView iv_pic_first = (ImageView) helper.getView(R.id.iv_pic_first);
//                ImageView iv_pic_second = (ImageView) helper.getView(R.id.iv_pic_second);
//                ImageView iv_pic_three = (ImageView) helper.getView(R.id.iv_pic_three);
//                if (StringUtils.isEmpty(item.getMsgVo().getTitleImgList())) {
//                    if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                        Glide.with(mFragment).load(item.getMsgVo().getTitleImg()).placeholder(R.drawable.news_default_bg).into(iv_pic_first);
//                    } else {
//                        iv_pic_first.setAdjustViewBounds(true);
//                        Glide.with(mFragment).load(item.getMsgVo().getTitleImg()).placeholder(R.drawable.news_default_bg)
//                                .crossFade().centerCrop().into(iv_pic_first);
//                    }
//                } else {
//                    String[] imgUrl = item.getMsgVo().getTitleImgList().split(",");
//                    if (imgUrl.length > 2) {
//                        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                            Glide.with(mFragment).load(imgUrl[0]).placeholder(R.drawable.news_default_bg).into(iv_pic_first);
//                            Glide.with(mFragment).load(imgUrl[1]).placeholder(R.drawable.news_default_bg).into(iv_pic_second);
//                            Glide.with(mFragment).load(imgUrl[2]).placeholder(R.drawable.news_default_bg).into(iv_pic_three);
//                        } else {
//                            iv_pic_first.setAdjustViewBounds(true);
//                            Glide.with(mFragment).load(imgUrl[0]).placeholder(R.drawable.news_default_bg)
//                                    .crossFade().centerCrop().into(iv_pic_first);
//                            iv_pic_second.setAdjustViewBounds(true);
//                            Glide.with(mFragment).load(imgUrl[1]).placeholder(R.drawable.news_default_bg)
//                                    .crossFade().centerCrop().into(iv_pic_second);
//                            iv_pic_three.setAdjustViewBounds(true);
//                            Glide.with(mFragment).load(imgUrl[2]).placeholder(R.drawable.news_default_bg)
//                                    .crossFade().centerCrop().into(iv_pic_three);
//                        }
//                    }
//
//                }

                break;

            case MsgVoMuti.VIDEO:
//                ImageLoader.getInstance().displayImage(mFragment, item.getMsgVo().getTitleImg(), helper.getView(R.id.video_snapshoot_iv));
//                if (!TextUtils.isEmpty(item.getMsgVo().getMsgTitle())){
//                    helper.setText(R.id.video_source_tv, item.getMsgVo().getMsgSource()).setText(R.id.video_title_tv,  StringUtils.replaceBlank(item.getMsgVo().getMsgTitle()));
//                }
//
////                if (item.isRead()){
////                    helper.setTextColor(R.id.video_title_tv, ContextCompat.getColor(context,R.color.text999));
////                }else {
////                    helper.setTextColor(R.id.video_title_tv, ContextCompat.getColor(context,R.color.textColorBackPrimary));
////                }
//                String remark = item.getMsgVo().getRemark();
//                if (!TextUtils.isEmpty(remark)) {
//                    Map<String, String> remarkMap = GsonUtils.GsonToMaps(remark);
//                    String duration1 = remarkMap.get("duration");
//                    SimpleDateFormat format = new SimpleDateFormat("mm:ss");//时间格式
//                    String duration = format.format(new Date(Integer.valueOf(duration1).intValue() * 1000));
//
//                    helper.setText(R.id.video_duration_tv, duration);
//                }
////                helper.addOnClickListener(R.id.video_share_iv);
//                //阅读数
//                if (item.getMsgVo().getReadNum() == 0) {
//                    helper.getView(R.id.tv_news_num).setVisibility(View.GONE);
//                    helper.setText(R.id.tv_news_num, "暂无数据");
//                } else {
//                    helper.getView(R.id.tv_news_num).setVisibility(View.VISIBLE);
//                    helper.setText(R.id.tv_news_num, item.getMsgVo().getReadNum()+"播放");
//                }
//                //时间
//                String timeV = item.getMsgVo().getDateTime();
//                if (TextUtils.isEmpty(timeV)) {
//                    helper.setText(R.id.tv_news_time, "暂无数据");
//                } else {
//                    helper.setText(R.id.tv_news_time, timeV);
//                }
                break;
        }

    }
}
