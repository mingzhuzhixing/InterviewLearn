package com.v.module_video.ffmpeg;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-06 11:28
 */
public class FFMPEGUtils {

    /**
     * 获取视频元数据Rotation
     */
    public native int getRotation(String path);

}
