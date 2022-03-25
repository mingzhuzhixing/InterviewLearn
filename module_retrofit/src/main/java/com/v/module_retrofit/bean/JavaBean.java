package com.v.module_retrofit.bean;

import java.util.List;


/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-04-25 12:01
 */

public class JavaBean {
    /**
     * web : []
     * query : car
     * translation : ["车"]
     * errorCode : 0
     * basic : {"us-phonetic":"kɑːr","phonetic":"kɑː(r)","uk-phonetic":"kɑː(r)","explains":["n. 汽车；车厢","n. (Car)人名；(土)贾尔；(法、西)卡尔；(塞)察尔"]}
     */
    private Basic basic;
    private String query;
    private List<String> translation;
    private int errorCode;

    public Basic getBasic() {
        return basic;
    }

    public void setBasic(Basic basic) {
        this.basic = basic;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getTranslation() {
        return translation;
    }

    public void setTranslation(List<String> translation) {
        this.translation = translation;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String toString() {
        return "JavaBean{" +
                "basic=" + basic +
                ", query='" + query + '\'' +
                ", translation=" + translation +
                ", errorCode=" + errorCode +
                '}';
    }

    public static class Basic{
        public String us_phonetic;
        public String phonetic;

        public String getUs_phonetic() {
            return us_phonetic;
        }

        public void setUs_phonetic(String us_phonetic) {
            this.us_phonetic = us_phonetic;
        }

        public String getPhonetic() {
            return phonetic;
        }

        public void setPhonetic(String phonetic) {
            this.phonetic = phonetic;
        }

        @Override
        public String toString() {
            return "Basic{" +
                    "us_phonetic='" + us_phonetic + '\'' +
                    ", phonetic='" + phonetic + '\'' +
                    '}';
        }
    }

}
