package com.v.module_rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.jakewharton.rxbinding2.view.RxView;
import com.v.module_rxjava.api.WanAndroidApi;
import com.v.module_rxjava.bean.ProjectBean;
import com.v.module_rxjava.bean.ProjectItem;
import com.v.module_rxjava.http.HttpRetrofit;
import com.v.module_rxjava.uitls.RxJavaUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@SuppressLint("CheckResult")
public class UserInfoActivity extends AppCompatActivity {
    private final String TAG = "UserInfoActivity";
    private WanAndroidApi api;
    private TextView tvResult;
    private Disposable disposable1, disposable2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        tvResult = findViewById(R.id.tv_result);

        api = HttpRetrofit.createRetrofit().create(WanAndroidApi.class);

        //初始化化防抖操作
        initDebounceAction();

        //初始化化防抖FlatMap使用
        initFlatMapNestingAction();

        //初始化化防抖FlatMap请求一个使用
        initFlatMapNestingItemAction();
    }

    /**
     * 获取所有数据
     */
    public void getProjectData(View view) {
        disposable1 = api.getProjectData()
                .compose(RxJavaUtils.applySchedulers())
                .subscribe(new Consumer<ProjectBean>() {
                    @Override
                    public void accept(ProjectBean projectBean) throws Exception {
                        if (projectBean != null) {
                            Log.i(TAG, "结果：" + projectBean.data.toString());
                            tvResult.setText(projectBean.data.toString());
                        }
                    }
                });
    }

    /**
     * 获取单个item数据
     */
    public void getProjectItem(View view) {
        disposable2 = api.getProjectItem(1, 294)
                .compose(RxJavaUtils.applySchedulers())
                .subscribe(new Consumer<ProjectItem>() {
                    @Override
                    public void accept(ProjectItem projectItem) throws Exception {
                        if (projectItem != null) {
                            Log.i(TAG, "结果：" + projectItem.toString());
                            tvResult.setText(projectItem.toString());
                        }
                    }
                });
    }

    /**
     * 初始化防抖
     */
    public void initDebounceAction() {
        Button debounceRequest = findViewById(R.id.debounce_request);
        RxView.clicks(debounceRequest)
                .throttleFirst(2, TimeUnit.SECONDS) //两秒内只取一个点击事件，防抖操作
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        api.getProjectData()
                                .compose(RxJavaUtils.applySchedulers())
                                .subscribe(new Consumer<ProjectBean>() {
                                    @Override
                                    public void accept(ProjectBean projectBean) throws Exception {
                                        if (projectBean != null) {
                                            for (ProjectBean.ProjectData data : projectBean.getData()) {
                                                api.getProjectItem(1, data.getId())
                                                        .compose(RxJavaUtils.applySchedulers())
                                                        .subscribe(new Consumer<ProjectItem>() {
                                                            @Override
                                                            public void accept(ProjectItem projectItem) throws Exception {
                                                                if (projectItem != null) {
                                                                    Log.i(TAG, "结果：" + projectItem.toString());
                                                                }
                                                            }
                                                        });
                                            }
                                        }
                                    }
                                });
                    }
                });
    }


    /**
     * 普通嵌套处理
     */
    public void normalNesting(View view) {
        api.getProjectData()
                .compose(RxJavaUtils.applySchedulers())
                .subscribe(new Consumer<ProjectBean>() {
                    @Override
                    public void accept(ProjectBean projectBean) throws Exception {
                        if (projectBean != null) {
                            for (ProjectBean.ProjectData data : projectBean.getData()) {
                                api.getProjectItem(1, data.getId())
                                        .compose(RxJavaUtils.applySchedulers())
                                        .subscribe(new Consumer<ProjectItem>() {
                                            @Override
                                            public void accept(ProjectItem projectItem) throws Exception {
                                                if (projectItem != null) {
                                                    Log.i(TAG, "结果：" + projectItem.toString());
                                                }
                                            }
                                        });
                            }
                        }
                    }
                });
    }

    /**
     * 防抖 网络请
     */
    public void initFlatMapNestingAction() {
        Button btnFlatMapNesting = findViewById(R.id.btn_flatMap_nesting);
        RxView.clicks(btnFlatMapNesting)
                .throttleFirst(2, TimeUnit.SECONDS)//两秒内只取一个点击事件，防抖操作

                //只给下面 切换 异步
                .observeOn(Schedulers.io())
                .flatMap(new Function<Object, ObservableSource<ProjectBean>>() {
                    @Override
                    public ObservableSource<ProjectBean> apply(Object o) throws Exception {
                        return api.getProjectData();
                    }
                })
                .flatMap(new Function<ProjectBean, Observable<ProjectBean.ProjectData>>() {
                    @Override
                    public Observable<ProjectBean.ProjectData> apply(ProjectBean projectBean) throws Exception {
                        //自己创建一个发射器，发送多次
                        return Observable.fromIterable(projectBean.getData());
                    }
                })
                .flatMap(new Function<ProjectBean.ProjectData, ObservableSource<ProjectItem>>() {
                    @Override
                    public ObservableSource<ProjectItem> apply(ProjectBean.ProjectData projectData) throws Exception {
                        return api.getProjectItem(1, projectData.getId());
                    }
                })

                //只给下面 切换 UI线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProjectItem>() {
                    @Override
                    public void accept(ProjectItem projectItem) throws Exception {
                        if (projectItem != null) {
                            Log.i(TAG, "结果：" + projectItem.toString());
                            tvResult.setText("结果 :initFlatMapNestingAction");
                        }
                    }
                });
    }

    /**
     * 初始化化防抖FlatMap请求一个使用
     */
    private void initFlatMapNestingItemAction() {
        Button btn_flatMap_nesting_once = findViewById(R.id.btn_flatMap_nesting_once);
        RxView.clicks(btn_flatMap_nesting_once)
                .throttleFirst(2, TimeUnit.SECONDS)//两秒内只取一个点击事件，防抖操作

                //只给下面 切换 异步
                .observeOn(Schedulers.io())
                .flatMap(new Function<Object, ObservableSource<ProjectBean>>() {
                    @Override
                    public ObservableSource<ProjectBean> apply(Object o) throws Exception {
                        return api.getProjectData();
                    }
                })
                .map(new Function<ProjectBean, ProjectBean.ProjectData>() {
                    @Override
                    public ProjectBean.ProjectData apply(ProjectBean projectBean) throws Exception {
                        if (projectBean != null && projectBean.getData().size() > 0) {
                            return projectBean.getData().get(0);
                        } else {
                            return new ProjectBean.ProjectData();
                        }
                    }
                })
                .flatMap(new Function<ProjectBean.ProjectData, ObservableSource<ProjectItem>>() {
                    @Override
                    public ObservableSource<ProjectItem> apply(ProjectBean.ProjectData projectData) throws Exception {
                        if (projectData != null && projectData.getId() > 0) {
                            return api.getProjectItem(1, projectData.getId());
                        } else {
                            return Observable.just(new ProjectItem());
                        }
                    }
                })

                //只给下面 切换 UI线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ProjectItem>() {
                    @Override
                    public void accept(ProjectItem projectItem) throws Exception {
                        if (projectItem != null) {
                            Log.i(TAG, "结果：" + projectItem.toString());
                            tvResult.setText(projectItem.toString());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable1 != null && !disposable1.isDisposed()) {
            disposable1.dispose();
        }

        if (disposable2 != null && !disposable2.isDisposed()) {
            disposable2.dispose();
        }
    }
}