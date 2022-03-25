package com.v.module_dagger.component;

import com.v.module_dagger.DaggerMainActivity;
import com.v.module_dagger.module.HttpRetrofitModule;

import dagger.Component;

/**
 * ClassName: AppComponent
 * Description:
 *
 * @author zhuming
 * @package_name com.youshu.dagger_module.component
 * @date 2021/9/27 6:35 下午
 */
@Component(modules = {HttpRetrofitModule.class})
public interface AppComponent {

    void injectDaggerMainActivity(DaggerMainActivity mainActivity);
}
