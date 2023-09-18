package com.v.module_database.realm.dao;

import com.v.module_database.realm.model.User;

import java.util.List;

/**
 * ClassName: UserDao
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_database.realm
 * @date 2023/9/17 12:00
 */
public interface UserDao {
    //插入
    void insert(User user) throws Exception;

    //查询
    List<User> getAllUser() throws Exception;

    //更新
    User updateUser(User User) throws Exception;

    //根据条件修改
    void updateUserByUserId(String userId, String userName, int age) throws Exception;

    //删除
    void deleteUser(String userId) throws Exception;

    //异步插入
    void insertUserAsync(User user) throws Exception;

    void closeDB();
}
