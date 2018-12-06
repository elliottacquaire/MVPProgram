/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.li.mvpprogram.BaseApplication;

import java.io.*;

/**
 * Created by lison on 8/8/16.
 */
public class ImageUtils {

    private ImageUtils() {
        throw new AssertionError();
    }

    /**
     * convert Bitmap to byte array
     *
     * @param b
     * @return
     */
    public static byte[] bitmapToByte(Bitmap b) {
        if (b == null) {
            return null;
        }

        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     *
     * @param b
     * @return
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * convert Drawable to Bitmap
     *
     * @param d
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable d) {
        return d == null ? null : ((BitmapDrawable) d).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable bitmapToDrawable(Bitmap b) {
        return b == null ? null : new BitmapDrawable(b);
    }

    /**
     * convert Drawable to byte array
     *
     * @param d
     * @return
     */
    public static byte[] drawableToByte(Drawable d) {
        return bitmapToByte(drawableToBitmap(d));
    }

    /**
     * convert byte array to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }


    /**
     * scale image
     *
     * @param org
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     *
     * @param org
     * @param scaleWidth  sacle of width
     * @param scaleHeight scale of height
     * @return
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }


    /**
     * 将Bitmap转换成Base64字符串
     *
     * @param bitmap
     * @return
     */
    public static String Bitmap2StrByBase64(Bitmap bitmap) {


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static String byte2StrBase64(byte[] source) {
        return Base64.encodeToString(source, Base64.NO_WRAP);
    }

    /**
     * bitmap to base64
     *
     * @param bitmap
     * @return
     */
    public static String byte2Base64String(Bitmap bitmap) {
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            return Base64.encodeToString(bos.toByteArray(), Base64.NO_WRAP);
        } catch (Exception e) {
            LogUtils.e(e.getMessage());
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (bitmap != null) {
                    bitmap.recycle();
                    bitmap = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    /**
     * 图片文件转base64字符串
     */
    public static String ImgFile2StrByBase64(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        if (!FileUtils.getInstance().isExists(filePath)) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
        if (bitmap == null) {
            return null;
        }
        return Bitmap2StrByBase64(bitmap);
    }


    /**
     * 处理图片
     *
     * @param filePath
     * @param degree
     * @return
     */
    public static Bitmap handPicture(String filePath, int degree) {

        if (TextUtils.isEmpty(filePath)) {
            return null;
        }
        //压缩图片
        Bitmap bitmap = getSmallBitmap(filePath);
        //旋转处理
        if (degree != 0) {
            return rotateBitmap(bitmap, degree);
        } else {
            return bitmap;
        }

    }

    ;


    /**
     * 获取图片角度
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * 图片旋转
     *
     * @param bitmap
     * @param degress
     * @return
     */
    public static Bitmap rotateBitmap(Bitmap bitmap, int degress) {
        try {
            if (bitmap != null) {
                Matrix m = new Matrix();
                m.postRotate(degress);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, true);
                return bitmap;
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }


    /**
     * 计算图片的缩放值
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * 压缩
     *
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        // options.inSampleSize = calculateInSampleSize(options,200,200);
        options.inSampleSize = 2;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }


    public static String saveBitmapToSD(Bitmap mBitmap, String name) {

        FileUtils.createInstance(BaseApplication.getInstance().getApplicationContext());
        if (TextUtils.isEmpty(FileUtils.getInstance().IMG_CACHE_HEADIMAGE_PATH)) {
            return null;
        }
        File mfile = new File(FileUtils.getInstance().IMG_CACHE_HEADIMAGE_PATH + name + ".png");
        try {
            mfile.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(mfile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        try {
            fOut.flush();
            return mfile.getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * base64转为bitmap
     *
     * @param base64Data
     * @return
     */
    public static Bitmap base64ToBitmap(String base64Data) {
        byte[] bytes = Base64.decode(base64Data, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    /**
     * 保存base64图片字符串到jpg文件
     *
     * @param base64Data 图片base64字符串
     * @param filePath   文件路径，包含文件名和文件后缀
     * @return
     */
    public static boolean base64ToImgFile(String base64Data, String filePath) {
        if (TextUtils.isEmpty(base64Data) || TextUtils.isEmpty(filePath)) {
            return false;
        }
        Bitmap bitmap = base64ToBitmap(base64Data);
        if (bitmap == null) {
            return false;
        }
        File file = new File(filePath);
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * 写入文件
     *
     * @param source
     * @param filePath
     */
    public static void writeToFile(String source, String filePath) {

        File file = new File(filePath);
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(file));
            ps.println(source);// 往文件里写入字符串
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    /**
     * 压缩图片，大小不大于指定的Limit Size
     *
     * @param bitmap
     * @param limitSize 单位M
     * @return
     */
    public static Bitmap compressBitmapToLimitSize(Bitmap bitmap, int limitSize) {
        double bitmapSize = (calculateSize(bitmap) * 1.0 / 8 / 1024 / 1024);
        if (bitmapSize <= limitSize) {
            return bitmap;
        }
        LogUtils.e("COMPRESS", "图片被压缩了");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        float zoom = (float) Math.sqrt(limitSize * 1024 / bos.toByteArray().length);

        Matrix matrix = new Matrix();
        matrix.setScale(zoom, zoom);

        Bitmap resultBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bos.reset();

        while (bos.toByteArray().length > limitSize * 1024) {
            matrix.setScale(0.9f, 0.9f);
            resultBitmap = Bitmap.createBitmap(resultBitmap, 0, 0, resultBitmap.getWidth(), resultBitmap.getHeight(), matrix, true);
            bos.reset();
            resultBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        }
        return resultBitmap;
    }

    /**
     * 计算bitmap大小
     *
     * @param bitmap
     * @return
     */
    public static int calculateSize(Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();
    }
}
