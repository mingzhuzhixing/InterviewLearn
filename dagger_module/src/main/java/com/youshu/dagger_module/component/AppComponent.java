package com.youshu.dagger_module.component;

import com.youshu.dagger_module.DaggerMainActivity;
import com.youshu.dagger_module.module.HttpRetrofitModule;

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
