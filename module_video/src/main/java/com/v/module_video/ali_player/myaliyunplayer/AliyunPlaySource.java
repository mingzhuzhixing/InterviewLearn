package com.v.module_video.ali_player.myaliyunplayer;

import com.v.module_video.ali_player.base.BasePlaySource;

public class AliyunPlaySource extends BasePlaySource {
    private String vid;
    private String akId;
    private String akSecret;
    private String token;


    public String getVid() {
        return vid;
    }

    public AliyunPlaySource setVid(String vid) {
        this.vid = vid;
        return this;
    }

    public String getAkId() {
        return akId;
    }

    public AliyunPlaySource setAkId(String akId) {
        this.akId = akId;
        return this;
    }

    public String getAkSecret() {
        return akSecret;
    }

    public AliyunPlaySource setAkSecret(String akSecret) {
        this.akSecret = akSecret;
        return this;
    }

    public String getToken() {
        return token;
    }

    public AliyunPlaySource setToken(String token) {
        this.token = token;
        return this;
    }

}
