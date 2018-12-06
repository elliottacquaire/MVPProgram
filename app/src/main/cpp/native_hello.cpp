#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_li_mvpprogram_SplashActivity_stringFromJNIToNative(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++， to native sfa";
    return env->NewStringUTF(hello.c_str());
}
