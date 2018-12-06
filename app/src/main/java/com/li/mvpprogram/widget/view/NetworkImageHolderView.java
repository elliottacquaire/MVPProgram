package com.li.mvpprogram.widget.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bigkoo.convenientbanner.holder.Holder;
import com.li.mvpprogram.HeadlineFragment;
import com.li.mvpprogram.R;
import com.li.mvpprogram.bean.BannerVo;
import com.li.mvpprogram.utils.imageloader.ImageLoader;

import static com.li.mvpprogram.discovery.HeadlinePresenter.BANNER_ID;


public class NetworkImageHolderView implements Holder<BannerVo> {
    private HeadlineFragment homeFragment;
    private ImageView imageView;
    private MyOnClickListener onClickListener;

    public NetworkImageHolderView(HeadlineFragment homeFragment) {
        this.homeFragment = homeFragment;
        onClickListener = new MyOnClickListener(homeFragment);
    }

    @Override
    public View createView(Context context) {
        imageView = new ImageView(context);
        //  imageView.setImageResource(R.drawable.ic_launcher);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView;
    }

    @Override
    public void UpdateUI(Context context, int position, BannerVo data) {
        // imageView.setImageResource(R.drawable.ic_launcher);
        if(null == data) {
            return;
        }
        if (data.getId() == null || data.getId() == BANNER_ID) {
             imageView.setImageResource(R.mipmap.home_default_banner);
//            data.setHrefUrl(ENVController.H5_URL + Constants.URL_MINE_CALL_MANAGER);
            ImageLoader.getInstance().displayImageNocenterCrop(homeFragment, data.getBannerImage(), R.mipmap.home_default_banner, imageView);
        } else {
            ImageLoader.getInstance().displayImageNocenterCrop(homeFragment, data.getBannerImage(), R.mipmap.home_default_banner, imageView);
        }
        onClickListener.setData(data);
        onClickListener.setPosition(position);
        imageView.setOnClickListener(onClickListener);
    }

    private static class MyOnClickListener implements View.OnClickListener {

        private BannerVo data;
        private int position;

        HeadlineFragment homeFragment;

        public MyOnClickListener(HeadlineFragment homeFragment) {
            this.homeFragment = homeFragment;
        }

        public BannerVo getData() {
            return data;
        }

        public void setData(BannerVo data) {
            this.data = data;
        }

        public void setPosition(int position) {
            this.position = position;
        }


        @Override
        public void onClick(View view) {
            if (data != null) {
                if (TextUtils.isEmpty(data.getHrefUrl()))return;
                homeFragment.startWebActivity(data.getHrefUrl());
            }
        }
    }
}
