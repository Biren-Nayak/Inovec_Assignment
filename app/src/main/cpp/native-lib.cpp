#include <jni.h>
#include <string>


extern "C"
JNIEXPORT jstring JNICALL
Java_com_example_inovecassignment_api_key_Keys_apiKey(JNIEnv *env, jobject thiz) {
    std::string api_key = "api key";
    return env->NewStringUTF(api_key.c_str());
}