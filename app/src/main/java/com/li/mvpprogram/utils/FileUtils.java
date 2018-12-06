/*
 * 乡邻小站
 *  Copyright (c) 2016 XiangLin,Inc.All Rights Reserved.
 */

package com.li.mvpprogram.utils;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.io.*;


/**
 * 文件工具类
 *
 * @author songdiyuan
 * @version $Id: XLFileUtils.java, v 1.0.0 2015-8-22 下午10:21:03 xl Exp $
 */
public class FileUtils {
    private final static String TAG = "FILESERVER";
    private final static String FILE_EXTENSION_SEPARATOR = ".";

    public File ImgCachePath;
    public File ImgSavePath;
    public File ImgSavePathPhoto;
    public File ImgThumbnailSavePath;
    public File ImgSharePath;
    public File ApkSavePath;
    public File LogSavePath;
    public File ImgCapTempPath;
    public File ImgCapCutPath;
    public File ImgCacheDefaultPath;
    public File VoiceCachePath;
    public File HeadImage_path;
    public File XLPublishCachePath;
    public File XLVIDEOCachePath;
    /**
     * 设备唯一标识did存储路径
     */
    public File Did_path;
    /**
     * webview照片存储路径
     */
    public File Browser_photo_path;

    public static String APP_DATA_ROOT_PATH;
    public static String IMG_SAVE_PATH;
    public static String IMG_SAVE_PATH_PHOTO; //用户拍照原图
    public static String IMG_THUMBNAIL_SAVE_PATH;
    public static String IMG_SHARE_PATH;
    public static String APK_INSTALL_PATH;
    public static String APK_LOG_PATH;
    public static String IMG_SAVE_PATH_CAP_TEMP;
    public static String IMG_SAVE_PATH_CAP_CUT;
    public static String IMG_CACHE_XUTILS_SDCARD_PATH;
    public static String IMG_CACHE_XUTILS_DEFAULT_PATH;
    public static String IMG_CACHE_HEADIMAGE_PATH;
    public static String FINAL_IMAGE_PATH;
    public static String FINAL_TEMP_PATH;
    public static String SDPATH;
    public static String VOICE_CACHE_PATH;
    public static String VIDEO_CACHE_PATH;
    /**
     * 设备唯一标识did存储路径
     */
    public static String DID_PATH;
    /**
     * webview 照片存储路径
     */
    public static String BROWSER_PHOTO_PATH;

    public static String XLPUBLISH_CACHE_PATH;

    public File XiangLinPath;
    public Context mContext;
    private static FileUtils mInstance;

    public FileUtils(Context context) {
        mContext = context;
    }

    /**
     * 创建文件工具类示例
     *
     * @param context 上下文
     * @return
     */
    public static synchronized FileUtils createInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FileUtils(context);
            mInstance.initPath();
        }
        return mInstance;
    }

    /**
     * 获取文件工具类实例
     *
     * @return
     */
    public static synchronized FileUtils getInstance() {
        if (mInstance == null)
            throw new IllegalStateException("FileUtil must be create by call createInstance(Context context)");
        return mInstance;
    }

    /**
     * 初始化本地缓存路径
     */
    public void initPath() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            SDPATH = Environment.getExternalStorageDirectory() + "/";
            IMG_SAVE_PATH = SDPATH + "XiangLin/images/save/";
            IMG_SAVE_PATH_PHOTO = SDPATH + "XiangLin/images/save/photo/";
            IMG_THUMBNAIL_SAVE_PATH = IMG_SAVE_PATH + "thumbnail/";
            IMG_SHARE_PATH = SDPATH + "XiangLin/images/share/";
            APK_INSTALL_PATH = SDPATH + "XiangLin/app/";
            APK_LOG_PATH = SDPATH + "XiangLin/log/";
            IMG_SAVE_PATH_CAP_TEMP = SDPATH + "XiangLin/images/save/capture/XiangLin_temp/";
            IMG_SAVE_PATH_CAP_CUT = SDPATH + "XiangLin/images/save/capture/XiangLin_cut/";
            IMG_CACHE_XUTILS_SDCARD_PATH = SDPATH + "XiangLin/images/cache/";// 用于保存图片缓存吧
            IMG_CACHE_XUTILS_DEFAULT_PATH = SDPATH + "Android/data/" + mContext.getPackageName() + "/cache/imgCache/";

            IMG_CACHE_HEADIMAGE_PATH = SDPATH + "XiangLin/images/save/headimage/";//保存用户头像

            VOICE_CACHE_PATH = SDPATH + "XiangLin/voice/cache/";

            DID_PATH = SDPATH + ".did/";

            BROWSER_PHOTO_PATH = SDPATH + "XiangLin/images/save/browser-photos/";

            XLPUBLISH_CACHE_PATH = getPathFiles() + "XLCircle";

            APP_DATA_ROOT_PATH = getAppPath() + "XiangLin/";
            FINAL_IMAGE_PATH = APP_DATA_ROOT_PATH + "images/";
            FINAL_TEMP_PATH = APP_DATA_ROOT_PATH + "temp/";
            VIDEO_CACHE_PATH = SDPATH + "XiangLin/video/cache/";

            XiangLinPath = new File(APP_DATA_ROOT_PATH);
            if (!XiangLinPath.exists()) {
                XiangLinPath.mkdirs();
            }
            XiangLinPath = new File(FINAL_IMAGE_PATH);
            if (!XiangLinPath.exists()) {
                XiangLinPath.mkdirs();
            }

            XiangLinPath = new File(FINAL_TEMP_PATH);
            if (!XiangLinPath.exists()) {
                XiangLinPath.mkdirs();
            }

            // 拍照图片保存地址
            ImgCapTempPath = new File(IMG_SAVE_PATH_CAP_TEMP);
            if (!ImgCapTempPath.exists()) {
                ImgCapTempPath.mkdirs();
            }
            // 裁剪后图片保存地址
            ImgCapCutPath = new File(IMG_SAVE_PATH_CAP_CUT);
            if (!ImgCapCutPath.exists()) {
                ImgCapCutPath.mkdirs();
            }
            // 图片保存、缓存地址
            ImgSavePath = new File(IMG_SAVE_PATH);
            if (!ImgSavePath.exists()) {
                ImgSavePath.mkdirs();
            }            // 用户拍照图片保存、缓存地址
            ImgSavePathPhoto = new File(IMG_SAVE_PATH_PHOTO);
            if (!ImgSavePathPhoto.exists()) {
                ImgSavePathPhoto.mkdirs();
            }
            // 图片缩略图保存地址
            ImgThumbnailSavePath = new File(IMG_THUMBNAIL_SAVE_PATH);
            if (!ImgThumbnailSavePath.exists()) {
                ImgThumbnailSavePath.mkdirs();
            }

            // 分享图片的临时保存路径
            ImgSharePath = new File(IMG_SHARE_PATH);
            if (!ImgSharePath.exists()) {
                ImgSharePath.mkdirs();
            }
            // 检测更新时保存路径
            ApkSavePath = new File(APK_INSTALL_PATH);
            if (!ApkSavePath.exists()) {
                ApkSavePath.mkdirs();
            }
            // 异常保存路径
            LogSavePath = new File(APK_LOG_PATH);
            if (!LogSavePath.exists()) {
                LogSavePath.mkdirs();
            }
            ImgCachePath = new File(IMG_CACHE_XUTILS_SDCARD_PATH);
            if (!ImgCachePath.exists()) {
                ImgCachePath.mkdirs();
            }
            ImgCacheDefaultPath = new File(IMG_CACHE_XUTILS_DEFAULT_PATH);
            if (!ImgCacheDefaultPath.exists()) {
                ImgCacheDefaultPath.mkdirs();
            }
            // 语音文件保存路径
            VoiceCachePath = new File(VOICE_CACHE_PATH);
            if (!VoiceCachePath.exists()) {
                VoiceCachePath.mkdirs();
            }
            // 头像保存路径
            HeadImage_path = new File(IMG_CACHE_HEADIMAGE_PATH);
            if (!HeadImage_path.exists()) {
                HeadImage_path.mkdirs();
            }

            // 设备唯一标识存储路径
            Did_path = new File(DID_PATH);
            if (!Did_path.exists()) {
                Did_path.mkdirs();
            }

            // webview 照片存储路径
            Browser_photo_path = new File(BROWSER_PHOTO_PATH);
            if (!Browser_photo_path.exists()) {
                Browser_photo_path.mkdirs();
            }
            // 微博发布缓存路径
            XLPublishCachePath = new File(XLPUBLISH_CACHE_PATH);
            if (!XLPublishCachePath.exists()) {
                XLPublishCachePath.mkdirs();
            }
            XLVIDEOCachePath = new File(VIDEO_CACHE_PATH);
            if(!XLVIDEOCachePath.exists()) {
                XLVIDEOCachePath.mkdirs();
            }

        }

    }

    private String getAppPath() {
        LogUtils.i("MyApplication-getAppPath():" + mContext.getFilesDir().getParent());
        return mContext.getFilesDir().getParent() + "/";
    }

    private String getPathFiles() {
        LogUtils.i("MyApplication-getPathFiles():" + mContext.getFilesDir().getAbsolutePath());
        return mContext.getFilesDir().getAbsolutePath() + "/";
    }

    /**
     * [将文件保存到SDcard方法]<BR>
     * [功能详细描述]
     *
     * @param fileName
     * @throws IOException
     */
    public boolean saveFile2SDCard(String fileName, byte[] dataBytes) throws IOException {
        boolean flag = false;
        FileOutputStream fs = null;
        try {
            if (!TextUtils.isEmpty(fileName)) {
                File file = newFileWithPath(fileName.toString());
                if (file.exists()) {
                    file.delete();
                    LogUtils.w("httpFrame  threadName:" + Thread.currentThread().getName() + " 文件已存在 则先删除: "
                            + fileName.toString());
                }
                fs = new FileOutputStream(file);
                fs.write(dataBytes, 0, dataBytes.length);
                fs.flush();
                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fs != null)
                fs.close();
        }

        return flag;
    }

    /**
     * 创建一个文件，如果其所在目录不存在时，他的目录也会被跟着创建
     *
     * @return
     * @author songdiyuan
     * @date 2015-8-24
     */
    public File newFileWithPath(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return null;
        }

        int index = filePath.lastIndexOf(File.separator);

        String path = "";
        if (index != -1) {
            path = filePath.substring(0, index);
            if (!TextUtils.isEmpty(path)) {
                File file = new File(path.toString());
                // 如果文件夹不存在
                if (!file.exists() && !file.isDirectory()) {
                    boolean flag = file.mkdirs();
                    if (flag) {
                        LogUtils.i("httpFrame  threadName:" + Thread.currentThread().getName() + " 创建文件夹成功："
                                + file.getPath());
                    } else {
                        LogUtils.e("httpFrame  threadName:" + Thread.currentThread().getName() + " 创建文件夹失败："
                                + file.getPath());
                    }
                }
            }
        }
        return new File(filePath);
    }

    /**
     * 判断文件是否存在
     *
     * @param strPath
     * @return
     */
    public boolean isExists(String strPath) {
        if (strPath == null) {
            return false;
        }

        final File strFile = new File(strPath);

        if (strFile.exists()) {
            return true;
        }
        return false;

    }

    public boolean deleteFile(String strPath) {
        if (strPath == null) {
            return false;
        }

        final File strFile = new File(strPath);

        if (strFile.exists()) {
            return strFile.delete();
        }
        return false;

    }

//    /**
//     * 下载文件
//     *
//     * @param context
//     * @param xlid
//     * @param fileId  需要下载的文件ID
//     * @param path    保存的路径
//     */
//    public static void downloadFile(Context context, long xlid, String fileId, String path, FileMessageListener<FileTask> listener) {
//        LogCatLog.d(TAG, "要下载的文件ID" + fileId);
//        if(fileId != null && !fileId.equals("null")) {
//            FileNetWork.getInstance()
//                    .initFileNetWorkUtilAddTask(new FileTask.Builder()
//                            .XLID(xlid)
//                            .fileID(fileId)   //test:"569"
//                            .fileToPath(path)     //test :    "/sdcard/"
//                            .fileStatus(FileNetWork.FILE_DOWNLOAD)
//                            .mContext(context)
//                            .listener(listener)
//                            .build());
//        }else{
//            LogCatLog.d(TAG,"文件ID 为null");
//        }
//    }
//
//
//
//    /**
//     * 下载文件
//     *
//     * @param context
//     * @param xlid
//     * @param fileId  需要下载的文件ID
//     * @param path    保存的路径
//     */
//    public  void downloadFiles(Context context, long xlid, String fileId, String path, FileMessageListener<FileTask> listener) {
//        LogCatLog.d(TAG, "要下载的文件ID" + fileId);
//        if(fileId != null && !fileId.equals("null")) {
//            FileNetWork.getInstance()
//                    .initFileNetWorkUtilAddTask(new FileTask.Builder()
//                            .XLID(xlid)
//                            .fileID(fileId)   //test:"569"
//                            .fileToPath(path)     //test :    "/sdcard/"
//                            .fileStatus(FileNetWork.FILE_DOWNLOAD)
//                            .mContext(context)
//                            .listener(listener)
//                            .build());
//        }else{
//            LogCatLog.d(TAG,"文件ID 为null");
//        }
//    }
//    /**
//     * 文件上传
//     *
//     * @param context
//     * @param path    需上传的文件（路径加文件名）
//     * @param xlid
//     */
//    public static void uploadFile(Context context, long xlid, String path, FileMessageListener<FileTask> listener) {
//        LogCatLog.d(TAG,"需要上传文件的"+path);
//       if (path != null && !path.equals("")) {
//           File f = new File(path);
//           File file = new File(f.getAbsoluteFile() + "");
//           FileNetWork.getInstance()
//                   .initFileNetWorkUtilAddTask(new FileTask.Builder()
//                           .mContext(context)
//                           .XLID(xlid)
//                           .fileStatus(FileNetWork.FILE_UPLOAD)
//                           .fileName(file.getName())
//                           .filePath(file.getAbsolutePath())
//                           .fileSize(file.length())
//                           .token(MD5FileUtil.getFileMD5String(file))
//                           .fileType("1")
//                           .listener(listener)
//                           .build());
//       }else{
//           LogCatLog.d(TAG,"要上传的文件路径不能为null");
//       }
//    }


    /**
     * 拷贝一个文件到另一个目录
     */
    public boolean copyFile(String from, String to) {

        File fromFile, toFile;
        fromFile = new File(from);
        toFile = new File(to);
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            toFile.createNewFile();
            fis = new FileInputStream(fromFile);
            fos = new FileOutputStream(toFile);
            int bytesRead;
            byte[] buf = new byte[4 * 1024];  // 4K buffer
            while ((bytesRead = fis.read(buf)) != -1) {
                fos.write(buf, 0, bytesRead);
            }
            fos.flush();
            fos.close();
            fis.close();
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
        return true;

    }

    /**
     * 删除文件
     *
     * @param path
     * @return
     */
    public static boolean removeFile(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                return file.delete();
            } else {
                LogUtils.d("删除文件失败");
            }
        } catch (Exception e) {
            LogUtils.e("删除文件失败" + e);
        }
        return false;
    }


    //===============h5上传图片===================================


    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    public static String getCachePath(Context context, @NonNull String dir) {
        boolean sdCardExist = Environment.getExternalStorageState().equals("mounted");
        File cacheDir = context.getExternalCacheDir();
        if (!sdCardExist || cacheDir == null || !cacheDir.exists() || !cacheDir.mkdirs()) {
            cacheDir = context.getCacheDir();
        }

        File tarDir = new File(cacheDir.getPath() + File.separator + dir);
        if (!tarDir.exists()) {
            boolean result = tarDir.mkdir();
            LogUtils.w(TAG, "getCachePath = " + tarDir.getPath() + ", result = " + result);
            if (!result) {
                tarDir = new File("/sdcard/cache/" + dir);
                if (!tarDir.exists()) {
                    result = tarDir.mkdirs();
                }

                LogUtils.e(TAG, "change path = " + tarDir.getPath() + ", result = " + result);
            }
        }

        return tarDir.getPath();
    }

    /**
     * 关闭流
     */
    @SuppressWarnings("WeakerAccess")
    public static void close(Closeable... closeables) {
        if (closeables == null || closeables.length == 0)
            return;
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 拷贝文件
     * 如果目标文件不存在将会自动创建
     *
     * @param srcFile  原文件
     * @param saveFile 目标文件
     * @return 是否拷贝成功
     */
    public static boolean copyFile(final File srcFile, final File saveFile) {
        File parentFile = saveFile.getParentFile();
        if (!parentFile.exists()) {
            if (!parentFile.mkdirs())
                return false;
        }

        BufferedInputStream inputStream = null;
        BufferedOutputStream outputStream = null;
        try {
            inputStream = new BufferedInputStream(new FileInputStream(srcFile));
            outputStream = new BufferedOutputStream(new FileOutputStream(saveFile));
            byte[] buffer = new byte[1024 * 4];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            close(inputStream, outputStream);
        }
        return true;
    }


    /**
     * 获取目录文件大小
     *
     * @param dir
     * @return
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String formatFileSize(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 转换文件大小以M为单位
     *
     * @param fileS
     * @return
     */
    public static String formatFileSizeWithMb(long fileS) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        String fileSizeString = "";
        fileSizeString = df.format((double) fileS / 1048576) + "M";
        return fileSizeString;
    }


    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 如果下面还有文件
                    File files[] = file.listFiles();
                    if (files == null) {
                        return;
                    }
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    //保存首帧图片到本地
    public String saveImageToLocal(Bitmap bmp) {
        String path = FileUtils.IMG_SAVE_PATH + "snapImg.jpg";
//        File appDir = new File(Environment.getExternalStorageDirectory(), "test");
//        if (!appDir.exists()) {
//            appDir.mkdir();
//        }
//        String fileName = "snapImg.jpg";
        File file = new File(path);
        String snapUrl = "";
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            snapUrl = file.getPath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return snapUrl;
    }

    /**
     * get file name from path not include suffix
     *
     * @param filePath
     * @return
     */
    public String getFileNameWithoutExtension(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }
        int extenPos = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePos = filePath.lastIndexOf(File.separator);
        if (filePos == -1) {
            if (extenPos == -1) {
                return filePath;
            }
            return filePath.substring(0, extenPos);
        }
        if (extenPos == -1) {
            return filePath.substring(filePos + 1);
        }
        if (filePos < extenPos) {
            return filePath.substring(filePos + 1, extenPos);
        }
        return filePath.substring(filePos + 1);
    }

    /**
     * save bitmap to sdcard
     *
     * @return
     */
    public String saveBitmapToSdcard(Bitmap bitmap, String filePath) throws IllegalAccessException {
        if (!SDCardUtils.isAvailable()) {
            throw new IllegalAccessException("SDCARD CAN NOT AVAILABLE");
        }
        if (bitmap == null) {
            throw new NullPointerException("BITMAP CAN NOT BE NULL");
        }
        if (StringUtils.isEmpty(filePath)) {
            throw new NullPointerException("FILEPATH CAN NOT BE NULL");
        }
        String originalPath = "";
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            file.delete();
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            originalPath = file.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return originalPath;
    }

    /**
     * 保存视频首帧
     *
     * @param bitmap
     * @param sourcePath
     * @return
     */
    public String saveVideoFrame(Bitmap bitmap, String sourcePath) {
        try {
            String filePath = FileUtils.IMG_SAVE_PATH + getFileNameWithoutExtension(sourcePath) + "_frame.jpg";
            return saveBitmapToSdcard(bitmap, filePath);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
