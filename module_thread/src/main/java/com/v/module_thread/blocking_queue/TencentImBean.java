package com.v.module_thread.blocking_queue;

/**
 * ClassName: TencentImBean
 * Description:
 *
 * @author wangbingqi
 * @package_name com.fengwo.function.tengcentIm.model
 * @date 2021/7/12 3:45 PM
 */
public class TencentImBean {
    //文本内容 type=businessText时text=JSON
    public String text;
    //消息类型 text,image,liveSystemNotice,businessText
    public String type;
    //昵称
    public String nick;
    //头像
    public String avatar;
    //用户ID
    public int userID;
    //anchor主播,admin管理员,member普通会员
    public String role;
    //[type = image 必填] 如果是图片类型，这里传图片 [type=liveSystemNotice]action的值说明： anchorEnterRoom：主播进入房间 anchorExitRoom：主播离开房间， anchorOpenCamera：主播打开摄像头， anchorCloseCamera：主播关闭摄像头， anchorStartPush：主播开始推流， anchorStopPush：主播停止推流 fileTranceCodeFinished:文件转码完成 userForbid : 用户被禁言 roomForbid : 聊天室禁言 onlineNums : 在线人数 ， body.text内容： {"OnlineNums":100,"OnlineSum":700} OnlineNums在线人数，OnlineSum在线总数 recall: 撤回消息
    public String img;
    //[type = liveSystemNotice时必填]
    public String action;
    //sendTime int64
    public long send_time;
    //来源
    public String source;
    //业务ID
    public String app;
    //消息引用透传，JSON类型，业务放可以自定义
    public String quote;
    //是否被隐藏 true：被隐藏的，普通用户不显示
    public boolean isHidden;
    //消息id
    public String msgId;


    @Override
    public String toString() {
        return "TencentImBean{" +
                "text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", nick='" + nick + '\'' +
                ", avatar='" + avatar + '\'' +
                ", userId='" + userID + '\'' +
                ", role='" + role + '\'' +
                ", img='" + img + '\'' +
                ", action='" + action + '\'' +
                ", sendTime=" + send_time +
                ", source='" + source + '\'' +
                ", app='" + app + '\'' +
                ", quote='" + quote + '\'' +
                ", isHidden=" + isHidden +
                ", msgId=" + msgId +
                '}';
    }
}
