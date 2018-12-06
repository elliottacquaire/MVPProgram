/**/

package com.li.mvpprogram.http;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.utils.ActivityManagerTool;
import com.li.mvpprogram.utils.EncryptUtils;
import com.li.mvpprogram.utils.ToastUtils;
import com.li.mvpprogram.utils.json.GsonUtils;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用接口助手类
 */
public class RpcHelper {

    public static final String OPERATION_TYPE = "operationType"; // http请求中参数key
    public static final String REQUEST_DATA = "requestData"; // http请求中参数key
    public static final String D = "d"; // http请求中参数key
    public static final String ACTION_RECEIVER_SESSION_EXPIRED_FAILED = "com.xianglin.app.improve.circle.service.action.receiver.SESSION_EXPIRED_FAILED";


    /**
     * 生成http请求参数map
     *
     * @param method      api接口方法全路径
     * @param requestBean 请求对象
     * @return 请求参数map
     */
    public static Map<String, String> getParamMap(
            String method,
            Object requestBean
    ) {
        String requestData = GsonUtils.DateBean2Json(requestBean);
        Map<String, String> map = new ArrayMap<>();
        map.put(OPERATION_TYPE, method);
        map.put(REQUEST_DATA, requestData);
        map.put(D, EncryptUtils.sha512(requestData));
        return map;
    }


    /**
     * Session过期处理
     */
    private static void startSessionExpired(Context context) {
        Intent intent = new Intent(ACTION_RECEIVER_SESSION_EXPIRED_FAILED);
        context.sendBroadcast(intent);
    }

    /**
     * 获取需要上传的文件组装后的map
     *
     * @param filePathList
     * @return
     */
    public static LinkedHashMap<String, RequestBody> getFileMap(ArrayList<String> filePathList) {
        if (filePathList == null || filePathList.isEmpty()) {
            return null;
        }
        LinkedHashMap<String, RequestBody> map = new LinkedHashMap<>();

        String filePath;
        for (int i = 0; i < filePathList.size(); i++) {
            filePath = filePathList.get(i);
            if (filePath == null) {
                continue;
            }
            File file = new File(filePath);
            if (!file.exists()) {
                ToastUtils.show(BaseApplication.getInstance().getApplicationContext(), file.getName() + "不存在！");
                continue;
            }
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);


            map.put(i + "_" + file.getName() + "\"; filename=\"" + i + "_" + file.getName(), requestFile);
        }

        return map;
    }

    /**
     * 借款=》私有图片上传
     *
     * @param filePath
     * @param partyId
     * @return
     */
    public static RequestBody getUploadPersonBody(String filePath, String partyId) {
        File file = new File(filePath);
        if (!file.exists()) {
            ToastUtils.show(BaseApplication.getInstance().getApplicationContext(), file.getName() + "不存在！");
            return null;
        }
        return new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .addFormDataPart("partyId", partyId)
                .build();
    }

    /**
     * 实名认证处理
     * 跳转实名认证页面
     */
    public static void IdAuthProcess() {
        List<Activity> activities = ActivityManagerTool.getActivityManager().getActivities();
        if (activities == null || activities.isEmpty()) {
            return;
        }
        Activity currentActivity = activities.get(activities.size() - 1);
        if (currentActivity == null) {
            return;
        }
        //跳转到实名认证页面
//        currentActivity.startActivity(MineCertificationActivity.makeIntent(currentActivity, null));
    }





}
