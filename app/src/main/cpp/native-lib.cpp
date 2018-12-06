#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_li_mvpprogram_SplashActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++， rrr";
    return env->NewStringUTF(hello.c_str());
}
