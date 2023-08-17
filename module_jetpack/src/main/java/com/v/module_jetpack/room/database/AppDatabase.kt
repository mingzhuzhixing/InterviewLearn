package com.v.module_jetpack.room.database

import android.content.Context
import android.util.Log
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.v.module_jetpack.room.dao.AddressDao
import com.v.module_jetpack.room.dao.FollowDao
import com.v.module_jetpack.room.dao.UserDao
import com.v.module_jetpack.room.table.Address
import com.v.module_jetpack.room.table.Follow
import com.v.module_jetpack.room.table.User

/**
 * ClassName: AppDatabase
 * Description:
 * @package_name com.v.kotlin_app.database
 * @author zhuming
 * @date 2021/9/28 4:51 下午
 *
 * 操作数据库进行数据库版本的升级和迁移
 * https://blog.51cto.com/u_15880918/5859981
 */
@Database(
    entities = [User::class, Address::class, Follow::class],
    version = 6,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "room_db.db"
        var INSTANCE: AppDatabase? = null

        /**
         * 初始化数据库
         *
         * allowMainThreadQueries() //强制要求在主线程运行(真实环境中不使用)  许在主线程中访问数据库
         * fallbackToDestructiveMigration()//破坏试的迁移.原有的数据被清空，重新建立新表
         */
        fun initApplication(context: Context) {
            if (INSTANCE == null) {
                Log.i("zm1234", "migrate: initApplication")
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .addMigrations(
                        MIGRATION_1_2,
                        MIGRATION_2_3,
                        MIGRATION_3_4,
                        MIGRATION_4_5,
                        MIGRATION_5_6
                    )
                    .build()
            }
        }

        fun getInstance(): AppDatabase? = INSTANCE

        /**
         * 版本号 1到2
         *
         * user 表增加 phone 字段
         */
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {

            override fun migrate(database: SupportSQLiteDatabase) {
                Log.i("zm1234", "migrate: MIGRATION_1_2")
                //使用下面分开的形式,可以正确执行
                database.execSQL("ALTER TABLE user ADD COLUMN phone TEXT DEFAULT NULL")
            }
        }

        /**
         * 版本号 2到3
         *
         * user 表增加 address 字段
         */
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.i("zm1234", "migrate: MIGRATION_2_3")
                database.execSQL("ALTER TABLE user ADD COLUMN address TEXT DEFAULT NULL")
            }
        }

        /**
         * 版本号 3到4
         *
         * 创建一个新表 follow
         */
        val MIGRATION_3_4: Migration = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.i("zm1234", "migrate: MIGRATION_3_4")
                //创建一个新表
                database.execSQL("CREATE TABLE follow(id INTEGER NOT NULL, user_id INTEGER NOT NULL, status INTEGER NOT NULL DEFAULT 0, PRIMARY KEY(id))")
            }
        }

        /**
         * 版本号 4到5
         * follow 增加字段 type 默认值 ""
         */
        val MIGRATION_4_5: Migration = object : Migration(4, 5) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.i("zm1234", "migrate: MIGRATION_4_5")
                //创建一个新表
                database.execSQL("ALTER TABLE follow ADD COLUMN type TEXT NOT NULL DEFAULT ''")
            }
        }

        /**
         * 版本号 5到6
         * user 修改age 类型 为字符串
         */
        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
            override fun migrate(database: SupportSQLiteDatabase) {
                Log.i("zm1234", "migrate: MIGRATION_5_6")

                //创建临时表
                database.execSQL(
                    "CREATE TABLE follow_temp(id INTEGER PRIMARY KEY NOT NULL, status TEXT, type TEXT NOT NULL DEFAULT '')"
                )
                Log.i("zm1234", "migrate: MIGRATION_5_6 --1")
                //迁移复制数据
                database.execSQL("INSERT INTO follow_temp(id,type,status) " +
                        "SELECT id,type,CAST(status as TEXT) FROM follow")
                Log.i("zm1234", "migrate: MIGRATION_5_6 --2")
                //删除旧表
                database.execSQL("DROP TABLE follow")
                Log.i("zm1234", "migrate: MIGRATION_5_6 --3")
                //临时表改名
                database.execSQL("ALTER TABLE follow_temp RENAME TO follow")
                Log.i("zm1234", "migrate: MIGRATION_5_6 --4")
            }
        }


        //成功
//        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                Log.i("zm1234", "migrate: MIGRATION_5_6")
//
//                //创建临时表
//                database.execSQL(
//                    "CREATE TABLE follow_temp(id INTEGER PRIMARY KEY NOT NULL, status TEXT)"
//                )
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --1")
//                //迁移复制数据
//                database.execSQL("INSERT INTO follow_temp(id,status) " +
//                        "SELECT id,CAST(status as TEXT) FROM follow")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --2")
//                //删除旧表
//                database.execSQL("DROP TABLE follow")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --3")
//                //临时表改名
//                database.execSQL("ALTER TABLE follow_temp RENAME TO follow")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --4")
//            }
//        }

//        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                Log.i("zm1234", "migrate: MIGRATION_5_6")
//
//                //创建临时表
//                database.execSQL(
//                    "CREATE TABLE follow_temp(id INTEGER PRIMARY KEY NOT NULL, user_id INTEGER , status TEXT, type TEXT)"
//                )
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --1")
//                //迁移复制数据
//                database.execSQL("INSERT INTO follow_temp(id,user_id,status,type) " +
//                        "SELECT id,user_id,CAST(status as TEXT),type FROM follow")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --2")
//                //删除旧表
//                database.execSQL("DROP TABLE follow")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --3")
//                //临时表改名
//                database.execSQL("ALTER TABLE follow_temp RENAME TO follow")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --4")
//            }
//        }

//        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                Log.i("zm1234", "migrate: MIGRATION_5_6")
//                //创建一个新表
////                database.execSQL("ALTER TABLE user ADD COLUMN age_new TEXT")
////                database.execSQL("UPDATE user SET age_new=CAST(age AS TEXT)")
////                database.execSQL("ALTER TABLE user DROP COLUMN age")
////                database.execSQL("ALTER TABLE user RENAME age_new TO age")
//
//                //创建临时表
//                database.execSQL(
//                    "CREATE TABLE user_temp(id INTEGER PRIMARY KEY NOT NULL, user_name TEXT DEFAULT NULL, " +
//                            "user_pwd TEXT DEFAULT NULL," +
//                            "sex TEXT DEFAULT NULL, age TEXT DEFAULT NULL," +
//                            "email TEXT DEFAULT NULL,school TEXT DEFAULT NULL," +
//                            "phone TEXT DEFAULT NULL,address TEXT DEFAULT NULL)"
//                )
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --1")
//                //迁移复制数据
//                database.execSQL("INSERT INTO user_temp(id,user_name,user_pwd,sex,age,email,school,phone,address) " +
//                        "SELECT id,user_name,user_pwd,sex,CAST(age as TEXT),email,school,phone,address FROM user")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --2")
//                //删除旧表
//                database.execSQL("DROP TABLE user")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --3")
//                //临时表改名
//                database.execSQL("ALTER TABLE user_temp RENAME TO user")
//                Log.i("zm1234", "migrate: MIGRATION_5_6 --4")
//            }
//        }

        //删除原有表中的字段 具体的版本迁移策略
//        val MIGRATION_5_6: Migration = object : Migration(5, 6) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                //重新创建一个表
//                database.execSQL("CREATE TABLE word_temp(id INTEGER PRIMARY KEY NOT NULL,english_word TEXT,chinese_meaning TEXT)")
//                //将word表中的数据 赋值到新表中
//                database.execSQL("INSERT INTO word_temp (id,english_word,chinese_meaning)" + "SELECT id,english_word,chinese_meaning FROM word")
//                //删除旧表word
//                database.execSQL("DROP TABLE word")
//                //将新表名该为原来的word表名
//                database.execSQL("ALTER TABLE word_temp RENAME to word")
//            }
//        }

    }

    abstract fun getUserDao(): UserDao

    abstract fun getAddressDao(): AddressDao

    abstract fun getFollowDao(): FollowDao

}