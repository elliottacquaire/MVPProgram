/**/

package com.li.mvpprogram.http;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.*;

import java.util.Map;
import java.util.TreeMap;

/**
 * [文件服务接口定义类]
 */
public interface FileService {

    /**
     * 多文件上传
     *
     * @param map
     * @return
     */
    @Multipart
    @POST("file/upload")
    Observable<TreeMap<String, String>> uploadFiles(@PartMap Map<String, RequestBody> map);

    /**
     * 多文件上传=>私有文件上传
     *
     * @param requestBody
     * @return
     */
    @POST("file/uploadPersonFile")
    Observable<String> uploadPersonFiles(@Body RequestBody requestBody);

    /**
     * 下载文件
     *
     * @param fileUrl
     * @return
     */
    @GET
    Observable<ResponseBody> downloadFiles(@Url String fileUrl);


}
