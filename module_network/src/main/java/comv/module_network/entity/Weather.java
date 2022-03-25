package comv.module_network.entity;

import java.util.List;

public class Weather {
    /**
     * postid : PHOT24LDT000100A
     * series :
     * clientadurl :
     * desc : 4月16日上午，四川达州的天空黑压压的，早晨9点却光线昏暗。达州人的朋友圈被黑云刷爆。
     * datatime : 2017-09-20 13:37:29
     * createdate : 2017-04-16 14:23:34
     * relatedids : []
     * scover : http://img3.cache.netease.com/photo/0001/2017-04-16/s_CI5B3HLG00AP0001.jpg
     * autoid :
     * url : http://news.163.com/photoview/00AP0001/2250173.html
     * creator : 耿旭娜
     * reporter :
     * photos : [{"timgurl":"http://img4.cache.netease.com/photo/0001/2017-04-16/t_CI5B3HLD00AP0001.jpg","photohtml":"http://news.163.com/photoview/00AP0001/2250173.html#p=CI5B3HLD00AP0001","newsurl":"#","squareimgurl":"http://img3.cache.netease.com/photo/0001/2017-04-16/400x400_CI5B3HLD00AP0001.jpg","cimgurl":"http://img4.cache.netease.com/photo/0001/2017-04-16/c_CI5B3HLD00AP0001.jpg","imgtitle":"","simgurl":"http://img4.cache.netease.com/photo/0001/2017-04-16/s_CI5B3HLD00AP0001.jpg","note":"4月16日上午，四川达州的天空黑压压的，早晨9点却光线昏暗。达州人的朋友圈被黑云刷爆。（来源：四川日报）","photoid":"CI5B3HLD00AP0001","imgurl":"http://img4.cache.netease.com/photo/0001/2017-04-16/CI5B3HLD00AP0001.jpg"}]
     */

    private String postid;
    private String series;
    private String clientadurl;
    private String desc;
    private String datatime;
    private String createdate;
    private String scover;
    private String autoid;
    private String url;
    private String creator;
    private String reporter;
    private List<?> relatedids;
    private List<WeatherPhoto> photos;

    public String getPostid() {
        return postid;
    }

    public void setPostid(String postid) {
        this.postid = postid;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getClientadurl() {
        return clientadurl;
    }

    public void setClientadurl(String clientadurl) {
        this.clientadurl = clientadurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDatatime() {
        return datatime;
    }

    public void setDatatime(String datatime) {
        this.datatime = datatime;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getScover() {
        return scover;
    }

    public void setScover(String scover) {
        this.scover = scover;
    }

    public String getAutoid() {
        return autoid;
    }

    public void setAutoid(String autoid) {
        this.autoid = autoid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public List<?> getRelatedids() {
        return relatedids;
    }

    public void setRelatedids(List<?> relatedids) {
        this.relatedids = relatedids;
    }

    public List<WeatherPhoto> getPhotos() {
        return photos;
    }

    public void setPhotos(List<WeatherPhoto> photos) {
        this.photos = photos;
    }
}
