package com.v.module_jetpack.sqlite.config

/**
 * ClassName: DbConfig
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_jetpack.sqlite
 * @date 2023/8/8 22:48
 */
class DBConfig {

    companion object {
        //数据库版本号
        const val dbVersion = 1

        //数据库名
        const val dbName = "school.db"

        //teacher数据表名
        const val teaTableName: String = "teacher"

        //student数据表名
        const val stuTableName: String = "student"
    }

}