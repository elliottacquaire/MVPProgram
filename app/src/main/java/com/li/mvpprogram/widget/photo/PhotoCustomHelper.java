package com.li.mvpprogram.widget.photo;

import android.net.Uri;
import android.os.Environment;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.TakePhotoOptions;

import java.io.File;


/**
 * 配置拍照参数
 *
 * @author pengyang
 * @version v 1.0.0 2017/1/5 下午4:01 XLXZ Exp $
 * @email pengyang@xianglin.cn
 */
public class PhotoCustomHelper {

    public static final int HEAD_PICTURE_TYPE_CAMERA = 1;//拍照
    public static final int HEAD_PICTURE_TYPE_ABULM = 2;//相册上传


    public static PhotoCustomHelper of() {
        return new PhotoCustomHelper();
    }

    private PhotoCustomHelper() {

        init();
    }

    private void init() {

    }

    public void onClick(int type, TakePhoto takePhoto) {
        File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
        Uri imageUri = Uri.fromFile(file);

        configCompress(takePhoto);
        configTakePhotoOption(takePhoto);
        switch (type) {
            case HEAD_PICTURE_TYPE_CAMERA:

                takePhoto.onPickFromCaptureWithCrop(imageUri, getCropOptions());
                break;
            case HEAD_PICTURE_TYPE_ABULM:

                takePhoto.onPickFromGalleryWithCrop(imageUri, getCropOptions());

                break;
            default:
                break;
        }
    }

    private void configTakePhotoOption(TakePhoto takePhoto) {
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();

        builder.setWithOwnGallery(false);// 使用系统图片选择器
        builder.setCorrectImage(true);//旋转图片

        takePhoto.setTakePhotoOptions(builder.create());

    }

    private void configCompress(TakePhoto takePhoto) {

        //不压缩
        takePhoto.onEnableCompress(null, false);


//        CompressConfig config;
//        config=new CompressConfig.Builder()
//                .setMaxSize(1024*1024*40)
//                .setMaxPixel(5000>=5000? 5000:5000)
//                .enableReserveRaw(false)//不保留原图
//                .create();
//
////        LubanOptions option = new LubanOptions.Builder()
////                .setMaxHeight(80000)
////                .setMaxWidth(80000)
////                .setMaxSize(1024000000)
////                .create();
////        config = CompressConfig.ofLuban(option);
////        config.enableReserveRaw(false);
//
//        takePhoto.onEnableCompress(config, false);


    }

    private CropOptions getCropOptions() {

        CropOptions.Builder builder = new CropOptions.Builder();
        builder.setOutputX(800).setOutputY(800);
        builder.setWithOwnCrop(true);//使用自带裁剪
        return builder.create();
    }

}
