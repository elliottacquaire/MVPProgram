#include "NativeEncrypt.h"
#include <assert.h>
#include <string.h>
#include <stdio.h>
#include "md5.h"
#include "SecurityManager.h"
#include "EncryptManager.h"
#include "UrlManager.h"

APK_INFO apkInfo;

void Java_com_li_mvpprogram_utils_NativeEncrypt_initEncryptLib(
        JNIEnv *env, jclass type, jobject context) {
//	LOGE("initLib  %s", "E");
    int len = -1;
    GetPackageInfo(env, context, &len, &apkInfo);

//	LOGE("initLib X signer is %s ", apkInfo.signer);
}

//jstring Java_com_li_mvpprogram_utils_NativeEncrypt_getPublicKEY(
//		JNIEnv* env, jobject thiz) {
//
//	if (CheckAuth(apkInfo) == 1) {
//		char * pubKey = getRsaPublicKey();
//		return (*env)->NewStringUTF(env, pubKey);
//	} else {
//		return (*env)->NewStringUTF(env, "app fail");
//	}
//
//}

jstring Java_com_li_mvpprogram_utils_NativeEncrypt_getAesKEY(
        JNIEnv *env, jobject thiz) {

    if (CheckAuth(apkInfo) == 1) {
        char *pubKey = getAesKey();
        return (*env)->NewStringUTF(env, pubKey);
    } else {
        return (*env)->NewStringUTF(env, "app fail");
    }

}

jstring Java_com_li_mvpprogram_utils_NativeEncrypt_gestureEncryptEnter(
        JNIEnv *env, jobject thiz, jstring path, jstring userid,
        jstring password) {
//	LOGE("gestureEncryptEnter %s ", "E");

    if (CheckAuth(apkInfo) == 1) {
        // Check path userid
        const char *_path = (*env)->GetStringUTFChars(env, path, NULL);
        const char *_userid = (*env)->GetStringUTFChars(env, userid, NULL);
        const char *_password = (*env)->GetStringUTFChars(env, password, NULL);
        if (_path != NULL && _userid != NULL) {
            // Initializing
            GESTURE_DATA *data_enter;
            data_enter = (GESTURE_DATA *) malloc(sizeof(GESTURE_DATA));

            memset(data_enter->loc_password, 0,
                   sizeof(data_enter->loc_password));
            memset(data_enter->loc_userid, 0, sizeof(data_enter->loc_userid));

            md5hexa(_userid, strlen(_userid), data_enter->loc_userid);
            md5hexa(_password, strlen(_password), data_enter->loc_password);

            // Create new file path
            char *f_path = (char *) malloc(
                    (strlen(_path) + 1) * sizeof(char)
                    + sizeof(data_enter->loc_userid));
            strcpy(f_path, _path);
            strcat(f_path, data_enter->loc_userid);

//			LOGE("new path is %s", f_path);

// Writing data
            FILE *fileWrite;
            if ((fileWrite = fopen(f_path, "w+")) == NULL) {
                // Release
                (*env)->ReleaseStringUTFChars(env, path, _path);
                (*env)->ReleaseStringUTFChars(env, userid, _userid);
                if (_password != NULL) {
                    (*env)->ReleaseStringUTFChars(env, password, _password);
                }
                free(data_enter);
                free(f_path);

                // Error!
                return (*env)->NewStringUTF(env, "fopen error");
            } else {
                fwrite(data_enter, sizeof(GESTURE_DATA), 1, fileWrite);
                fclose(fileWrite);

                //			LOGE("data_enter->loc_userid %s", data_enter->loc_userid);
                //			LOGE("data_enter->loc_password %s", data_enter->loc_password);

                // Release
                (*env)->ReleaseStringUTFChars(env, path, _path);
                (*env)->ReleaseStringUTFChars(env, userid, _userid);
                if (_password != NULL) {
                    (*env)->ReleaseStringUTFChars(env, password, _password);
                }
                free(data_enter);
                free(f_path);
                //			LOGE("gestureEncryptEnter %s ", "X");
                return (*env)->NewStringUTF(env, "enter success");
            }
        } else {
            return (*env)->NewStringUTF(env, "enter fail");
        }

    } else {
        return (*env)->NewStringUTF(env, "app fail");
    }

}

jstring Java_com_li_mvpprogram_utils_NativeEncrypt_gestureEncryptCheck(
        JNIEnv *env, jobject thiz, jstring path, jstring userid,
        jstring password) {
//	LOGE("gestureEncryptCheck %s ", "E");

    if (CheckAuth(apkInfo) == 1) {
        // Check path userid
        const char *_path = (*env)->GetStringUTFChars(env, path, NULL);
        const char *_userid = (*env)->GetStringUTFChars(env, userid, NULL);
        const char *_password = (*env)->GetStringUTFChars(env, password, NULL);
        if (_path != NULL && _userid != NULL) {
            // Initializing
            GESTURE_DATA *data_check;
            data_check = (GESTURE_DATA *) malloc(sizeof(GESTURE_DATA));

            memset(data_check->loc_password, 0,
                   sizeof(data_check->loc_password));
            memset(data_check->loc_userid, 0, sizeof(data_check->loc_userid));

            md5hexa(_userid, strlen(_userid), data_check->loc_userid);
            md5hexa(_password, strlen(_password), data_check->loc_password);

            // Create new file path
            char *f_path = (char *) malloc(
                    (strlen(_path) + 1) * sizeof(char)
                    + sizeof(data_check->loc_userid));

            strcpy(f_path, _path);
            strcat(f_path, data_check->loc_userid);

//			LOGE("new path is %s", f_path);

            // Reading data
            GESTURE_DATA *data_local;
            data_local = (GESTURE_DATA *) malloc(sizeof(GESTURE_DATA));

            FILE *fileRead;
            if ((fileRead = fopen(f_path, "rb")) == NULL) {
                // Error!
                return (*env)->NewStringUTF(env, "none gesture");
            }

            fread(data_local, sizeof(GESTURE_DATA), 1, fileRead);
            fclose(fileRead);

            // Check
            if (data_local->loc_userid == NULL
                || data_local->loc_password == NULL) {
                // Release
                (*env)->ReleaseStringUTFChars(env, path, _path);
                (*env)->ReleaseStringUTFChars(env, userid, _userid);
                if (_password != NULL) {
                    (*env)->ReleaseStringUTFChars(env, password, _password);
                }

                // Free
                free(data_check);
                free(data_local);
                free(f_path);

                return (*env)->NewStringUTF(env, "none gesture");
            } else {
                //			LOGE("data_local->loc_userid %s", data_local->loc_userid);
                //			LOGE("data_check->loc_userid %s", data_check->loc_userid);
                //			LOGE("data_local->loc_password %s", data_local->loc_password);
                //			LOGE("data_check->loc_password %s", data_check->loc_password);

                // Compare
                if (strcasecmp(data_local->loc_userid, data_check->loc_userid)
                    == 0
                    && strcasecmp(data_local->loc_password,
                                  data_check->loc_password) == 0) {
                    // Release
                    (*env)->ReleaseStringUTFChars(env, path, _path);
                    (*env)->ReleaseStringUTFChars(env, userid, _userid);
                    if (_password != NULL) {
                        (*env)->ReleaseStringUTFChars(env, password, _password);
                    }

                    // Free
                    free(data_check);
                    free(data_local);
                    free(f_path);
                    //			LOGE("gestureEncryptCheck %s ", "X");
                    return (*env)->NewStringUTF(env, "right gesture");
                } else {
                    // Release
                    (*env)->ReleaseStringUTFChars(env, path, _path);
                    (*env)->ReleaseStringUTFChars(env, userid, _userid);
                    if (_password != NULL) {
                        (*env)->ReleaseStringUTFChars(env, password, _password);
                    }

                    // Free
                    free(data_check);
                    free(data_local);
                    free(f_path);
                    //			LOGE("gestureEncryptCheck %s ", "X");
                    return (*env)->NewStringUTF(env, "error gesture");
                }

            }

        } else {
            return (*env)->NewStringUTF(env, "check fail");
        }

    } else {
        return (*env)->NewStringUTF(env, "app fail");
    }
}

jstring Java_com_li_mvpprogram_utils_NativeEncrypt_gestureEncryptUserExist(
        JNIEnv *env, jobject thiz, jstring path, jstring userid) {
//	LOGE("gestureEncryptUserExist %s ", "E");

    if (CheckAuth(apkInfo) == 1) {
        // Check path userid
        const char *_path = (*env)->GetStringUTFChars(env, path, NULL);
        const char *_userid = (*env)->GetStringUTFChars(env, userid, NULL);
        if (_path != NULL && _userid != NULL) {
            // Initializing
            GESTURE_DATA *data_exist;
            data_exist = (GESTURE_DATA *) malloc(sizeof(GESTURE_DATA));

            memset(data_exist->loc_password, 0,
                   sizeof(data_exist->loc_password));
            memset(data_exist->loc_userid, 0, sizeof(data_exist->loc_userid));

            md5hexa(_userid, strlen(_userid), data_exist->loc_userid);

            // Create new file path
            char *f_path = (char *) malloc(
                    (strlen(_path) + 1) * sizeof(char)
                    + sizeof(data_exist->loc_userid));
            strcpy(f_path, _path);
            strcat(f_path, data_exist->loc_userid);

//			LOGE("new path is %s", f_path);

            // Checking data
            if ((access(f_path, 0)) != -1) {
                // Release
                (*env)->ReleaseStringUTFChars(env, path, _path);
                (*env)->ReleaseStringUTFChars(env, userid, _userid);

                // Free
                free(data_exist);
                free(f_path);
//				LOGE("gestureEncryptUserExist %s ", "X");
                (*env)->NewStringUTF(env, "exist true");
            } else {
                // Release
                (*env)->ReleaseStringUTFChars(env, path, _path);
                (*env)->ReleaseStringUTFChars(env, userid, _userid);

                // Free
                free(data_exist);
                free(f_path);
//				LOGE("gestureEncryptUserExist %s ", "X");
                return (*env)->NewStringUTF(env, "exist false");
            }
        } else {
            return (*env)->NewStringUTF(env, "exist fail");
        }

    } else {
        return (*env)->NewStringUTF(env, "app fail");
    }
}

jstring Java_com_li_mvpprogram_utils_NativeEncrypt_gestureEncryptUserDelete(
        JNIEnv *env, jobject thiz, jstring path, jstring userid) {

    //	LOGE("gestureEncryptUserDelete %s ", "E");

    if (CheckAuth(apkInfo) == 1) {
        // Check path userid
        const char *_path = (*env)->GetStringUTFChars(env, path, NULL);
        const char *_userid = (*env)->GetStringUTFChars(env, userid, NULL);
        if (_path != NULL && _userid != NULL) {
            // Initializing
            GESTURE_DATA *data_delete;
            data_delete = (GESTURE_DATA *) malloc(sizeof(GESTURE_DATA));

            memset(data_delete->loc_password, 0,
                   sizeof(data_delete->loc_password));
            memset(data_delete->loc_userid, 0, sizeof(data_delete->loc_userid));

            md5hexa(_userid, strlen(_userid), data_delete->loc_userid);

            // Create new file path
            char *f_path = (char *) malloc(
                    (strlen(_path) + 1) * sizeof(char)
                    + sizeof(data_delete->loc_userid));
            strcpy(f_path, _path);
            strcat(f_path, data_delete->loc_userid);

            //			LOGE("new path is %s", f_path);

            // Checking data then delete file
            if ((access(f_path, 0)) != -1 && remove(f_path) != -1) {

                // Release
                (*env)->ReleaseStringUTFChars(env, path, _path);
                (*env)->ReleaseStringUTFChars(env, userid, _userid);

                // Free
                free(data_delete);
                free(f_path);
                //				LOGE("gestureEncryptUserDelete %s ", "X");
                (*env)->NewStringUTF(env, "delete true");
            } else {
                // Release
                (*env)->ReleaseStringUTFChars(env, path, _path);
                (*env)->ReleaseStringUTFChars(env, userid, _userid);

                // Free
                free(data_delete);
                free(f_path);
                //				LOGE("gestureEncryptUserDelete %s ", "X");
                return (*env)->NewStringUTF(env, "delete false");
            }
        } else {
            return (*env)->NewStringUTF(env, "delete fail");
        }

    } else {
        return (*env)->NewStringUTF(env, "app fail");
    }
}

jstring Java_com_li_mvpprogram_utils_NativeEncrypt_getUrlByKey(
        JNIEnv *env, jobject thiz, jstring key) {

    if (CheckAuth(apkInfo) == 1) {
        // Check path userid
        const char *_key = (*env)->GetStringUTFChars(env, key, NULL);
        if (_key != NULL) {
            char *outUrl = getUrlByKey(_key);
            if (outUrl != NULL) {
                return (*env)->NewStringUTF(env, outUrl);
            } else {
                return (*env)->NewStringUTF(env, "find fail");
            }
        } else {
            return (*env)->NewStringUTF(env, "find fail");
        }

    } else {
        return (*env)->NewStringUTF(env, "app fail");
    }
}

jstring Java_com_li_mvpprogram_utils_NativeEncrypt_getRsaByKey(
        JNIEnv *env, jobject thiz, jstring key) {

    if (CheckAuth(apkInfo) == 1) {
        // Check path userid
        const char *_key = (*env)->GetStringUTFChars(env, key, NULL);
        if (_key != NULL) {
            char *outRsa = getRsaByKey(_key);
            if (outRsa != NULL) {
                return (*env)->NewStringUTF(env, outRsa);
            } else {
                return (*env)->NewStringUTF(env, "find fail");
            }
        } else {
            return (*env)->NewStringUTF(env, "find fail");
        }

    } else {
        return (*env)->NewStringUTF(env, "app fail");
    }
}

