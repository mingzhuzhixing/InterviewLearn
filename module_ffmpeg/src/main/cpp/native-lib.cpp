#include <jni.h>
#include <string>


// 有坑，会报错，必须混合编译
// #include <libavutil/avutil.h>

extern "C" {
    #include <libavutil/avutil.h>
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_kevin_ndk14_1code_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = av_version_info();

    return env->NewStringUTF(hello.c_str());
}
