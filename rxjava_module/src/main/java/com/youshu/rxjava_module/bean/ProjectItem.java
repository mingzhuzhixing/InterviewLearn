package com.youshu.rxjava_module.bean;

import java.util.List;

/**
 * ClassName: ProjectItem
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.rxjava_module.bean
 * @date 2021/6/24 6:37 PM
 */
public class ProjectItem{
    private ProjectData data;

    public ProjectData getData() {
        return data;
    }

    public void setData(ProjectData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ProjectItem{" +
                "data=" + data +
                '}';
    }

    class ProjectData{
        private int curPage;
        private List<ProjectDataItem> datas;

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public List<ProjectDataItem> getDatas() {
            return datas;
        }

        public void setDatas(List<ProjectDataItem> datas) {
            this.datas = datas;
        }

        @Override
        public String toString() {
            return "ProjectData{" +
                    "curPage=" + curPage +
                    ", datas=" + datas +
                    '}';
        }
    }

    class ProjectDataItem{
        /**
         * shareDate : 1623768707000
         * projectLink : https://github.com/wo5813288/wan_giao
         * prefix :
         * canEdit : false
         * origin :
         * link : https://www.wanandroid.com/blog/show/3020
         * title : Flutter开发的WanAndroid
         * type : 0
         * selfVisible : 0
         * apkLink :
         * envelopePic : https://www.wanandroid.com/blogimgs/e092cd25-3e43-42c4-a7eb-b1ebc60ce02a.png
         * audit : 1
         * chapterId : 294
         * host :
         * realSuperChapterId : 293
         * id : 18624
         * courseId : 13
         * superChapterName : 开源项目主Tab
         * descMd :
         * publishTime : 1623768707000
         * niceShareDate : 2021-06-15 22:51
         * visible : 1
         * niceDate : 2021-06-15 22:51
         * author : wo5813288
         * zan : 0
         * chapterName : 完整项目
         * userId : -1
         * tags : [{"name":"项目","url":"/project/list/1?cid=294"}]
         * superChapterId : 294
         * fresh : false
         * collect : false
         * shareUser :
         * desc : 更新学习flutter，所以系统的做一款应用来实践一下。这款应用也开发了很多内容了，后续还要继续更新功能。开发这个项目主要也是熟悉flutter的树形结构的写法和UI组件，项目中也用到了flutter比较流行的第三方框架。
         */
        private long shareDate;
        private String projectLink;
        private String prefix;
        private boolean canEdit;
        private String origin;
        private String link;
        private String title;
        private int type;
        private int selfVisible;
        private String apkLink;
        private String envelopePic;
        private int audit;
        private int chapterId;
        private String host;
        private int realSuperChapterId;
        private int id;
        private int courseId;
        private String superChapterName;
        private String descMd;
        private long publishTime;
        private String niceShareDate;
        private int visible;
        private String niceDate;
        private String author;
        private int zan;
        private String chapterName;
        private int userId;
        private List<TagsEntity> tags;
        private int superChapterId;
        private boolean fresh;
        private boolean collect;
        private String shareUser;
        private String desc;

        public void setShareDate(long shareDate) {
            this.shareDate = shareDate;
        }

        public void setProjectLink(String projectLink) {
            this.projectLink = projectLink;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void setCanEdit(boolean canEdit) {
            this.canEdit = canEdit;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setSelfVisible(int selfVisible) {
            this.selfVisible = selfVisible;
        }

        public void setApkLink(String apkLink) {
            this.apkLink = apkLink;
        }

        public void setEnvelopePic(String envelopePic) {
            this.envelopePic = envelopePic;
        }

        public void setAudit(int audit) {
            this.audit = audit;
        }

        public void setChapterId(int chapterId) {
            this.chapterId = chapterId;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public void setRealSuperChapterId(int realSuperChapterId) {
            this.realSuperChapterId = realSuperChapterId;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setCourseId(int courseId) {
            this.courseId = courseId;
        }

        public void setSuperChapterName(String superChapterName) {
            this.superChapterName = superChapterName;
        }

        public void setDescMd(String descMd) {
            this.descMd = descMd;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public void setNiceShareDate(String niceShareDate) {
            this.niceShareDate = niceShareDate;
        }

        public void setVisible(int visible) {
            this.visible = visible;
        }

        public void setNiceDate(String niceDate) {
            this.niceDate = niceDate;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public void setZan(int zan) {
            this.zan = zan;
        }

        public void setChapterName(String chapterName) {
            this.chapterName = chapterName;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public void setTags(List<TagsEntity> tags) {
            this.tags = tags;
        }

        public void setSuperChapterId(int superChapterId) {
            this.superChapterId = superChapterId;
        }

        public void setFresh(boolean fresh) {
            this.fresh = fresh;
        }

        public void setCollect(boolean collect) {
            this.collect = collect;
        }

        public void setShareUser(String shareUser) {
            this.shareUser = shareUser;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public long getShareDate() {
            return shareDate;
        }

        public String getProjectLink() {
            return projectLink;
        }

        public String getPrefix() {
            return prefix;
        }

        public boolean isCanEdit() {
            return canEdit;
        }

        public String getOrigin() {
            return origin;
        }

        public String getLink() {
            return link;
        }

        public String getTitle() {
            return title;
        }

        public int getType() {
            return type;
        }

        public int getSelfVisible() {
            return selfVisible;
        }

        public String getApkLink() {
            return apkLink;
        }

        public String getEnvelopePic() {
            return envelopePic;
        }

        public int getAudit() {
            return audit;
        }

        public int getChapterId() {
            return chapterId;
        }

        public String getHost() {
            return host;
        }

        public int getRealSuperChapterId() {
            return realSuperChapterId;
        }

        public int getId() {
            return id;
        }

        public int getCourseId() {
            return courseId;
        }

        public String getSuperChapterName() {
            return superChapterName;
        }

        public String getDescMd() {
            return descMd;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public String getNiceShareDate() {
            return niceShareDate;
        }

        public int getVisible() {
            return visible;
        }

        public String getNiceDate() {
            return niceDate;
        }

        public String getAuthor() {
            return author;
        }

        public int getZan() {
            return zan;
        }

        public String getChapterName() {
            return chapterName;
        }

        public int getUserId() {
            return userId;
        }

        public List<TagsEntity> getTags() {
            return tags;
        }

        public int getSuperChapterId() {
            return superChapterId;
        }

        public boolean isFresh() {
            return fresh;
        }

        public boolean isCollect() {
            return collect;
        }

        public String getShareUser() {
            return shareUser;
        }

        public String getDesc() {
            return desc;
        }

        public class TagsEntity {
            /**
             * name : 项目
             * url : /project/list/1?cid=294
             */
            private String name;
            private String url;

            public void setName(String name) {
                this.name = name;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getName() {
                return name;
            }

            public String getUrl() {
                return url;
            }
        }

        @Override
        public String toString() {
            return "ProjectDataItem{" +
                    "shareDate=" + shareDate +
                    ", projectLink='" + projectLink + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", canEdit=" + canEdit +
                    ", origin='" + origin + '\'' +
                    ", link='" + link + '\'' +
                    ", title='" + title + '\'' +
                    ", type=" + type +
                    ", selfVisible=" + selfVisible +
                    ", apkLink='" + apkLink + '\'' +
                    ", envelopePic='" + envelopePic + '\'' +
                    ", audit=" + audit +
                    ", chapterId=" + chapterId +
                    ", host='" + host + '\'' +
                    ", realSuperChapterId=" + realSuperChapterId +
                    ", id=" + id +
                    ", courseId=" + courseId +
                    ", superChapterName='" + superChapterName + '\'' +
                    ", descMd='" + descMd + '\'' +
                    ", publishTime=" + publishTime +
                    ", niceShareDate='" + niceShareDate + '\'' +
                    ", visible=" + visible +
                    ", niceDate='" + niceDate + '\'' +
                    ", author='" + author + '\'' +
                    ", zan=" + zan +
                    ", chapterName='" + chapterName + '\'' +
                    ", userId=" + userId +
                    ", tags=" + tags +
                    ", superChapterId=" + superChapterId +
                    ", fresh=" + fresh +
                    ", collect=" + collect +
                    ", shareUser='" + shareUser + '\'' +
                    ", desc='" + desc + '\'' +
                    '}';
        }
    }
}
