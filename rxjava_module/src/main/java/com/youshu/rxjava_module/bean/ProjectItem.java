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
public class ProjectItem {
    /**
     * visible : 0
     * children : []
     * name : 完整项目
     * userControlSetTop : false
     * id : 294
     * courseId : 13
     * parentChapterId : 293
     * order : 145000
     */
    private int visible;
    private String name;
    private boolean userControlSetTop;
    private int id;
    private int courseId;
    private int parentChapterId;
    private int order;

    public void setVisible(int visible) {
        this.visible = visible;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserControlSetTop(boolean userControlSetTop) {
        this.userControlSetTop = userControlSetTop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setParentChapterId(int parentChapterId) {
        this.parentChapterId = parentChapterId;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getVisible() {
        return visible;
    }

    public String getName() {
        return name;
    }

    public boolean isUserControlSetTop() {
        return userControlSetTop;
    }

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getParentChapterId() {
        return parentChapterId;
    }

    public int getOrder() {
        return order;
    }

}
