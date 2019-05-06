//
// Created by ZhuMing on 2019/5/6.
//
//#include <jni.h>
//
//#include "ffmpeg.h"
//
//#define LOGI(format,...) __android_log_print(ANDROID_LOG_INFO,"jason",format,##__VA_ARGS__)
//#define LOGE(format,...) __android_log_print(ANDROID_LOG_ERROR,"jason",format,##__VA_ARGS__)
//
////JNIEnv JavaVM 区别
//JNIEXPORT jint JNICALL Java_com_v_ffmpeg_FFMPEGUtils_getRotation(
//        JNIEnv *env, jobject thiz,jstring videoPath){
//        //打开视频文件，读取元数据
//    LOGI("%s","getRotation");
//
//    //jstring->char*
//
//    const char* filepath=(*env)->GetStringUTFChars(env,videwPath,NULL);
//    LOGI("视频路径：%s",filepath)
//
//    //1、
//    av_register_all();
//
//    //2、解封装
//    AVFormatContext *pFormatCtx = avformat_alloc_context();
//
//    //3、打开
//    if(avformat_open_input(pFormatCtx,NULL) < 0){
//        LOGE("%s","无法打开视屏文件")
//        return -1;
//    }
//
//    //4、
//    if(avformat_find_stream_info(pFormatCtx,NULL)<0){
//        LOGE("%s","无法打开获取视屏文件信息")
//        return -1;
//    }
//
//    //5、获取视屏流索引
//    int v_stream_idx=-1;
//    for (int i = 0; i <pFormatCtx->nb_streams; ++i) {
//        if(pFormatCtx->streams[i]->codec->codec_type==AVMEDIA_TYPE_VIDEO){
//            v_stream_idx=i;
//            break;
//        }
//    }
//
//    if(v_stream_idx==-1){
//        LOGE("%s","无法获取视屏流");
//    }
//
////    6、解码器
////   AVCodecContext *pCodecCtx = pFormatCtx->streams[v_stream_idx]->codec;
//
//
//    //6、获取视屏元数据
//    AVDictionaryEntry *tag=NULL;
//    tag = av_dict_get(pFormatCtx->streams[v_stream_idx]->metadata,"rotate",tag,0);
//
//    int angle =-1;
//    if(tag!=null){
//        angle = atoi(tag->value);
//    }
//
//    //7、释放资源
//    avformat_free_context(pFormatCtx);
//
//
//
//}