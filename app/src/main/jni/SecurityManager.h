#include <jni.h>
#include <stdlib.h>
#include <stdio.h>
#include <android/log.h>

#ifndef _Included_SecurityManager
#define _Included_SecurityManager

#define LOG_TAG    "JNI_LOG"
#undef LOG
#define LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGI(...)  __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGW(...)  __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define LOGE(...)  __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define LOGF(...)  __android_log_print(ANDROID_LOG_FATAL,LOG_TAG,__VA_ARGS__)

typedef struct _APK_INFO {
    /**
     * apk包名
     */
    char *packageName;
    /**
     * apk versionCode
     */
    char *versionCode;
    /**
     * apk签名
     */
    char signer[33];
} APK_INFO;

typedef struct _GESTURE_DATA {
    char loc_userid[33];
    char loc_password[33];
} GESTURE_DATA;

char *GetPackageInfo(JNIEnv *env, jobject thiz, int *len, APK_INFO *apkInfo);

int CheckAuth(APK_INFO apkInfo);

#endif
