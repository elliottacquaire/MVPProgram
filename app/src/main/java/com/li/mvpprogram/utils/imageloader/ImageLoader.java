/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils.imageloader;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.li.mvpprogram.utils.LogUtils;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import java.io.File;

/**
 * 图片加载工具
 * Created by lison on 8/11/16.
 */
public class ImageLoader {

    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";


    private ImageLoader() {
    }

    private static class ImageLoaderHolder {
        private static final ImageLoader INSTANCE = new ImageLoader();
    }

    public static final ImageLoader getInstance() {
        return ImageLoaderHolder.INSTANCE;
    }

    /**
     * 加载网络图片,fitCenter
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void displayImage(Context context, String url, ImageView imageView, boolean fitCenter) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        if (!fitCenter) return;
        Glide.with(context)
                .load(url)
                .fitCenter()
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片,fitCenter
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public void displayImage(Fragment fragment, String url, ImageView imageView, boolean fitCenter) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        if (!fitCenter) return;
        Glide.with(fragment)
                .load(url)
                .fitCenter()
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片,centerCrop
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void displayImage(Context context, String url, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片,centerCrop
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public void displayImage(Fragment fragment, String url, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .centerCrop()
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void displayImage(Context context, String url, int errResID, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .error(errResID)
                .into(imageView);
    }


    /**
     * 加载网络图片
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public void displayImage(Fragment fragment, String url, int errResID, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .centerCrop()
                .crossFade()
                .error(errResID)
                .into(imageView);
    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void displayImageOnCircle(Context context, String url, int placeholderResid, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .placeholder(placeholderResid)
                .into(imageView);
    }

    /**
     * 加载网络图片
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public void displayImageOnCircle(Fragment fragment, String url, int placeholderResid, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .centerCrop()
                .crossFade()
                .placeholder(placeholderResid)
                .into(imageView);
    }

    @Deprecated
    public void displayImageNocenterCrop(Context context, String url, int errResID, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                //.centerCrop()
                .crossFade()
                .error(errResID)
                .into(imageView);
    }


    public void displayImageNocenterCrop(Fragment fragment, String url, int errResID, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                //.centerCrop()
                .crossFade()
                .error(errResID)
                .into(imageView);
    }

    /**
     * 加载SD卡图片
     *
     * @param context
     * @param file
     * @param imageView
     */
    @Deprecated
    public void displayImage(Context context, File file, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(file)
                .centerCrop()
                .into(imageView);
    }


    /**
     * 加载SD卡图片
     *
     * @param fragment
     * @param file
     * @param imageView
     */
    public void displayImage(Fragment fragment, File file, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(file)
                .centerCrop()
                .into(imageView);
    }

    /**
     * 加载SD卡图片并设置大小
     *
     * @param context
     * @param file
     * @param imageView
     * @param width
     * @param height
     */
    @Deprecated
    public void displayImage(Context context, File file, ImageView imageView, int width, int height) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(file)
                .override(width, height)
                .centerCrop()
                .into(imageView);

    }


    /**
     * 加载SD卡图片并设置大小
     *
     * @param fragment
     * @param file
     * @param imageView
     * @param width
     * @param height
     */
    public void displayImage(Fragment fragment, File file, ImageView imageView, int width, int height) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(file)
                .override(width, height)
                .centerCrop()
                .into(imageView);

    }

    /**
     * 加载网络图片并设置大小
     *
     * @param context
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    @Deprecated
    public void displayImage(Context context, String url, ImageView imageView, int width, int height) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片并设置大小
     *
     * @param fragment
     * @param url
     * @param imageView
     * @param width
     * @param height
     */
    public void displayImage(Fragment fragment, String url, ImageView imageView, int width, int height) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .centerCrop()
                .override(width, height)
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载drawable图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    @Deprecated
    public void displayImage(Context context, int resId, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(resourceIdToUr(context, resId))
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载drawable图片
     *
     * @param fragment
     * @param resId
     * @param imageView
     */
    public void displayImage(Fragment fragment, int resId, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(resourceIdToUr(fragment.getContext(), resId))
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载drawable图片显示为圆形图片
     *
     * @param context
     * @param resId
     * @param imageView
     */
    @Deprecated
    public void displayCircleImage(Context context, int resId, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(resourceIdToUr(context, resId))
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载drawable图片显示为圆形图片
     *
     * @param fragment
     * @param resId
     * @param imageView
     */
    public void displayCircleImage(Fragment fragment, int resId, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(resourceIdToUr(fragment.getContext(), resId))
                .crossFade()
                .transform(new GlideCircleTransform(fragment.getContext()))
                .into(imageView);
    }

    /**
     * 加载drawable图片显示为圆形图片(有默认图片)
     *
     * @param context
     * @param resId
     * @param errResID
     * @param imageView
     */
    @Deprecated
    public void displayCircleImage(Context context, int resId, int errResID, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(resourceIdToUr(context, resId))
                .error(errResID)
                .crossFade()
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }

    /**
     * 加载drawable图片显示为圆形图片(有默认图片)
     *
     * @param fragment
     * @param resId
     * @param errResID
     * @param imageView
     */
    public void displayCircleImage(Fragment fragment, int resId, int errResID, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(resourceIdToUr(fragment.getContext(), resId))
                .error(errResID)
                .crossFade()
                .transform(new GlideCircleTransform(fragment.getContext()))
                .into(imageView);
    }

    /**
     * 加载网络图片显示为圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void displayCircleImage(Context context, String url, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .transform(new GlideCircleTransform(context))
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片显示为圆形图片
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public void displayCircleImage(Fragment fragment, String url, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .transform(new GlideCircleTransform(fragment.getContext()))
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载网络图片显示为圆形图片(有默认图)
     *
     * @param context
     * @param url
     * @param errResID
     * @param imageView
     */
    @Deprecated
    public void displayCircleImage(Context context, String url, int errResID, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .error(errResID)
                .transform(new GlideCircleTransform(context))
                .crossFade()
                .into(imageView);
    }


    /**
     * 加载网络图片显示为圆形图片(有默认图)
     *
     * @param fragment
     * @param url
     * @param errResID
     * @param imageView
     */
    public void displayCircleImage(Fragment fragment, String url, int errResID, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .error(errResID)
                .transform(new GlideCircleTransform(fragment.getContext()))
                .crossFade()
                .into(imageView);
    }

    /**
     * 加载SD卡图片显示为圆形图片
     *
     * @param context
     * @param file
     * @param imageView
     */
    @Deprecated
    public void displayCircleImage(Context context, File file, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(file)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }


    /**
     * 加载SD卡图片显示为圆形图片
     *
     * @param fragment
     * @param file
     * @param imageView
     */
    public void displayCircleImage(Fragment fragment, File file, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(file)
                .transform(new GlideCircleTransform(fragment.getContext()))
                .into(imageView);
    }

    /**
     * 加载SD卡图片显示为圆形图片(带默认图)
     *
     * @param context
     * @param file
     * @param errResID
     * @param imageView
     */
    @Deprecated
    public void displayCircleImage(Context context, File file, int errResID, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(file)
                .error(errResID)
                .transform(new GlideCircleTransform(context))
                .into(imageView);
    }


    /**
     * 加载SD卡图片显示为圆形图片(带默认图)
     *
     * @param fragment
     * @param file
     * @param errResID
     * @param imageView
     */
    public void displayCircleImage(Fragment fragment, File file, int errResID, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(file)
                .error(errResID)
                .transform(new GlideCircleTransform(fragment.getContext()))
                .into(imageView);
    }


    public Uri resourceIdToUr(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }


    /**
     * 四周圆角
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void displayRounedCornersImage(Context context, String url, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .centerCrop()
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(context, 30, 0))
                .into(imageView);
    }


    /**
     * 四周圆角
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public void displayRounedCornersImage(Fragment fragment, String url, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .centerCrop()
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(fragment.getContext(), 30, 0))
                .into(imageView);
    }

    /**
     * 四周圆角
     *
     * @param context
     * @param url
     * @param imageView
     */
    @Deprecated
    public void displayRoundCornersImageNoCenterCrop(Context context, String url, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(url)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(context, 10, 0))
                .into(imageView);
    }

    /**
     * 四周圆角
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public void displayRoundCornersImageNoCenterCrop(Fragment fragment, String url, ImageView imageView) {
        if (fragment == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(fragment)
                .load(url)
                .crossFade()
                .bitmapTransform(new RoundedCornersTransformation(fragment.getContext(), 10, 0))
                .into(imageView);
    }

    /**
     * 展示本地图片
     *
     * @param activity
     * @param path
     * @param imageView
     */
    public void displayImage(Activity activity, String path, ImageView imageView) {
        if (null == activity) {
            return;
        }
        Glide.with(activity)
                .load(path)
                .fitCenter()
//                .placeholder(R.drawable.icon_my_station)
//                .error(R.drawable.grid_image_default)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 加载网络图片显示为圆形图片(有默认图)
     *
     * @param context
     * @param uri
     * @param errResID
     * @param imageView
     */
    public void displayCircleImage(Context context, Uri uri, int errResID, ImageView imageView) {
        if (context == null) {
            LogUtils.e("ImageLoader context is null");
            return;
        }
        Glide.with(context)
                .load(uri)
                .error(errResID)
                .transform(new GlideCircleTransform(context))
                .crossFade()
                .into(imageView);
    }
}
