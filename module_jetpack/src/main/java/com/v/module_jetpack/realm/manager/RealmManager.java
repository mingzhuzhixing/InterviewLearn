package com.v.module_jetpack.realm.manager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * ClassName: RealmManager
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_database.realm
 * @date 2023/9/17 11:57
 */
public class RealmManager {
    private final String realm_name = "realm_person.realm";

    private RealmManager() {
    }

    private static class SingletonFactory {
        private static final RealmManager instance = new RealmManager();
    }

    public static RealmManager getInstance() {
        return SingletonFactory.instance;
    }

    public Realm getRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(realm_name) //文件名
                .schemaVersion(1)
                .build();
        return Realm.getInstance(config);
    }
}
