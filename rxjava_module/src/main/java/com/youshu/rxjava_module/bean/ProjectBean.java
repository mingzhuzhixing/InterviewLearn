package com.youshu.rxjava_module.bean;

import java.util.List;

/**
 * ClassName: ProjectBean
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.rxjava_module.bean
 * @date 2021/6/24 6:29 PM
 */
public class ProjectBean {
    public List<ProjectItem> data;

    public List<ProjectItem> getData() {
        return data;
    }

    public void setData(List<ProjectItem> data) {
        this.data = data;
    }
}
