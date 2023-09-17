package com.v.module_database.realm.dao;

import android.content.Context;

import com.v.module_database.realm.manager.RealmManager;
import com.v.module_database.realm.model.User;

import java.util.List;

//import io.realm.Realm;

/**
 * ClassName: UserDaoImpl
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_database.realm
 * @date 2023/9/17 12:01
 */
public class UserDaoImpl implements UserDao {
    private Context context;
//    private Realm mRealm;

    public UserDaoImpl(Context context) {
//        mRealm = RealmManager.getInstance().getRealm();
    }

    @Override
    public void insert(User user) throws Exception {
//        mRealm.beginTransaction();
//        User user1 = mRealm.copyToRealm(user);
//        mRealm.commitTransaction();
//        mRealm.close();
    }

    @Override
    public List<User> getAllUser() throws Exception {
//        List<User> list = mRealm.where(User.class).findAll();
//        mRealm.close();
//        return list;
        return null;
    }

    @Override
    public User updateUser(User user) throws Exception {
//        mRealm.beginTransaction();
//        User user1 = mRealm.copyToRealmOrUpdate(user);
//        mRealm.commitTransaction();
//        mRealm.close();
//        return user1;
        return null;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
//        User user = mRealm.where(User.class).equalTo("user_id", userId).findFirst();
//        mRealm.beginTransaction();
//        user.deleteFromRealm();
//        mRealm.commitTransaction();
    }

    @Override
    public void insertUserAsync(User user) throws Exception {
        //一个Realm只能在同一个线程中访问，在子线程中进行数据库操作必须重新获取Realm对象：
//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.beginTransaction();
//                User user1 = realm.copyToRealm(user);
//                realm.commitTransaction();
//                realm.close();//并且要记得在离开线程时要关闭 realm.close();
//            }
//        });
//        //关闭Realm对象
//        mRealm.close();
    }
}
