#include "SecurityManager.h"
#include "md5.h"

#define RELEASE_KEY_MD5 "7c222d24dbb3843bb77f1f470962fa28"


int CheckAuth(APK_INFO apkInfo) {
    if (strcasecmp(apkInfo.signer, RELEASE_KEY_MD5) == 0) {
//		LOGE("App check success!");
        return 1;
    } else {

//		LOGE("App check fail!");
        // Now do not check app
        return 1;
    }
}

char *GetPackageInfo(JNIEnv *env, jobject thiz, int *len, APK_INFO *apkInfo) {
    //Java PackageManager pm = context.getPackageManager();
//	LOGE("GetPackageInfo %s" , "E");
    jclass cls_Context = (*env)->GetObjectClass(env, thiz);
    jmethodID getPackageManager = (*env)->GetMethodID(env, cls_Context,
                                                      "getPackageManager",
                                                      "()Landroid/content/pm/PackageManager;");
    jobject pm = (*env)->CallObjectMethod(env, thiz, getPackageManager);

    //Java context.getPackageName()
    jmethodID getPackageName = (*env)->GetMethodID(env, cls_Context,
                                                   "getPackageName", "()Ljava/lang/String;");
    jstring jpackagename = (jstring) (*env)->CallObjectMethod(env, thiz,
                                                              getPackageName);
    apkInfo->packageName = (*env)->GetStringUTFChars(env, jpackagename, 0);
    //Java PackageManager.GET_SIGNATURES
    jclass cls_PackageManager = (*env)->GetObjectClass(env, pm);
    // jfieldID GET_SIGNATURES_ID = env->GetStaticFieldID(cls_PackageManager, "GET_SIGNATURES", "I");
    // jint GET_SIGNATURES = env->GetStaticIntField(cls_PackageManager, GET_SIGNATURES_ID);
    jint GET_SIGNATURES = 64;
    //Java info = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
    jmethodID getPackageInfo = (*env)->GetMethodID(env, cls_PackageManager,
                                                   "getPackageInfo",
                                                   "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    jobject info = (*env)->CallObjectMethod(env, pm, getPackageInfo,
                                            jpackagename, GET_SIGNATURES);

    //Java info.signatures[0]
    jclass cls_PackageInfo = (*env)->GetObjectClass(env, info);
    jfieldID signatures_id = (*env)->GetFieldID(env, cls_PackageInfo,
                                                "signatures", "[Landroid/content/pm/Signature;");
    jobjectArray signatures = (jobjectArray) (*env)->GetObjectField(env, info,
                                                                    signatures_id);
    jfieldID versionCode_id = (*env)->GetFieldID(env, cls_PackageInfo,
                                                 "versionName", "Ljava/lang/String;");
    jstring versionCode = (jstring) (*env)->GetObjectField(env, info,
                                                           versionCode_id);
    apkInfo->versionCode = (*env)->GetStringUTFChars(env, versionCode, 0);
    jobject signature_0 = (*env)->GetObjectArrayElement(env, signatures, 0);

    jclass cls_Signature = (*env)->GetObjectClass(env, signature_0);
    jmethodID toByteArray = (*env)->GetMethodID(env, cls_Signature,
                                                "toByteArray", "()[B");
    jbyteArray signature_0_bytearray = (jbyteArray) (*env)->CallObjectMethod(env,
                                                                             signature_0,
                                                                             toByteArray);
    jsize sign_len = (*env)->GetArrayLength(env, signature_0_bytearray);
    *len = sign_len;
    char *sign = (char *) malloc(sign_len);
    (*env)->GetByteArrayRegion(env, signature_0_bytearray, 0, sign_len,
                               (jbyte *) sign);
    memset(apkInfo->signer, 0, sizeof(apkInfo->signer));
    md5hexa(sign, sign_len, apkInfo->signer);
//	LOGE("apkInfo->signer %s" , apkInfo->signer);
//	LOGE("GetPackageInfo %s" , "X");
    free(sign);
    return sign;
}

