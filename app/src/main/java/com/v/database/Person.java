package com.v.database;

import com.v.database.annotion.DbFiled;
import com.v.database.annotion.DbTable;

/**
 * Class description here
 *
 * @author zhuming
 * @site www.hdzuoye.com
 * @company 北京千阳远望信息技术有限公司
 * @date 2019-05-07 14:38
 */

@DbTable("tb_person2")
public class Person {

    @DbFiled("tb_name")
    public String name;

    @DbFiled("tb_password")
    public Long password;

    @DbFiled("tb_age")
    public int age;

    @DbFiled("tb_picture")
    public byte[] picture;

    public Person() {
    }

    public Person(String name, Long password, int age) {
        this.name = name;
        this.password = password;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPassword() {
        return password;
    }

    public void setPassword(Long password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
