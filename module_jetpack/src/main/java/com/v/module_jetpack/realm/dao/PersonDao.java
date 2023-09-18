package com.v.module_jetpack.realm.dao;


import com.v.module_jetpack.realm.model.Person;

import java.util.List;

/**
 * ClassName: UserDao
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_database.realm
 * @date 2023/9/17 12:00
 */
public interface PersonDao {
    //插入
    void insert(Person user) throws Exception;

    //查询
    List<Person> getAllUser() throws Exception;

    //更新
    Person updateUser(Person User) throws Exception;

    //根据条件修改
    void updateUserByUserId(String userId, String userName, int age) throws Exception;

    //删除
    void deleteUser(String userId) throws Exception;

    //异步插入
    void insertUserAsync(Person user) throws Exception;

    void closeDB();
}
