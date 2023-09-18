package com.v.module_database.realm.dao;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.v.module_database.realm.manager.RealmManager;
import com.v.module_database.realm.model.User;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * ClassName: UserDaoImpl
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_database.realm
 * @date 2023/9/17 12:01
 */
public class UserDaoImpl implements UserDao {
    private final Context context;
    private final Realm mRealm;

    public UserDaoImpl(Context context) {
        this.context = context;
        mRealm = RealmManager.getInstance().getRealm();
//        mRealm = getRealm();
        Log.d("zm1234", "UserDaoImpl mRealm:" + mRealm);
    }

    public Realm getRealm() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("realm_demo.realm") //文件名
                .allowQueriesOnUiThread(true)
                .allowWritesOnUiThread(true)
                .schemaVersion(1)
                .build();
        return Realm.getInstance(config);
    }

    @Override
    public void insert(User user) throws Exception {
        mRealm.beginTransaction();
        //判断用户是否存在
        RealmQuery<User> queryUser = mRealm.where(User.class);
        queryUser = queryUser.equalTo("user_id", user.getUser_id()).and().equalTo("name", user.getName());
        User findUser = queryUser != null ? queryUser.findFirst() : null;
        Log.i("zm1234", "insert: " + (findUser != null ? findUser.toString() : "用户不存在") + " queryUser:" + queryUser);
        if (findUser == null) {
            User insertUser = mRealm.copyToRealm(user);
        } else {
            Toast.makeText(context, "用户已存在", Toast.LENGTH_SHORT).show();
        }
        mRealm.commitTransaction();
        closeDB();
    }


    @Override
    public List<User> getAllUser() throws Exception {
        return mRealm.where(User.class).findAll();
    }

    /**
     * java.lang.IllegalArgumentException: A RealmObject with no @PrimaryKey cannot be updated: class com.v.module_database.realm.model.User
     * realm.copyToRealmOrUpdate(obj); 使用这个方法需要Model有主键, 会更新obj的主键对应的对象, 如果不存在则新建对象.
     */
    @Override
    public User updateUser(User user) throws Exception {
        mRealm.beginTransaction();
        User updateUser = mRealm.copyToRealmOrUpdate(user);
        mRealm.commitTransaction();
        closeDB();
        return updateUser;
    }

    /**
     * updateUserByUserId
     */

    @Override
    public void updateUserByUserId(String userId, String userName, int age) throws Exception {
//        mRealm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                //先查找后得到User对象
//                User user = mRealm.where(User.class).findFirst();
//                user.setAge(26);
//            }
//        });

        //获取查询的对象实例
        User userInfo = mRealm.where(User.class).equalTo("user_id", userId).findFirst();
        if (userInfo == null) {
            return;
        }
        mRealm.beginTransaction();//开启事务
        userInfo.setName(userName);//修改
        userInfo.setAge(age);//修改
        mRealm.commitTransaction();//提交事务
        closeDB();
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        User user = mRealm.where(User.class).equalTo("user_id", userId).findFirst();
        if (user == null) {
            return;
        }
        mRealm.beginTransaction();
        user.deleteFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public void insertUserAsync(User user) throws Exception {
        //一个Realm只能在同一个线程中访问，在子线程中进行数据库操作必须重新获取Realm对象：
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.beginTransaction();
                User user1 = realm.copyToRealm(user);
                realm.commitTransaction();
                realm.close();//并且要记得在离开线程时要关闭 realm.close();
            }
        });
        closeDB();
    }

    /**
     * 关闭Realm对象
     */
    @Override
    public void closeDB() {
        if (mRealm != null && !mRealm.isClosed()) {
            mRealm.close();
        }
    }
}