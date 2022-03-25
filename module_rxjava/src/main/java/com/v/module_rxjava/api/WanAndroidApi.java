package com.v.module_rxjava.api;
import com.v.module_rxjava.bean.ProjectBean;
import com.v.module_rxjava.bean.ProjectItem;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * ClassName: WanAndroidApi
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.rxjava_module
 * @date 2021/6/24 6:26 PM
 */
public interface WanAndroidApi {

    @GET("project/tree/json")
    Observable<ProjectBean> getProjectData();

    @GET("project/list/{pageIndex}/json")
    Observable<ProjectItem> getProjectItem(@Path("pageIndex") int pageIndex, @Query("cid") int cid);
}
