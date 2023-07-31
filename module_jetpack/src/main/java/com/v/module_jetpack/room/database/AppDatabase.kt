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
import com.v.module_jetpack.room.dao.UserDao
import com.v.module_jetpack.room.table.Address
import com.v.module_jetpack.room.table.User

/**
 * ClassName: AppDatabase
 * Description:
 * @package_name com.v.kotlin_app.database
 * @author zhuming
 * @date 2021/9/28 4:51 下午
 */
@Database(
    entities = [User::class, Address::class],
    version = 2
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao(): UserDao

    abstract fun getAddressDao(): AddressDao

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
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                    .addMigrations(MIGRATION_1_2)
                    .build()
            }
        }

        fun getInstance(): AppDatabase? = INSTANCE

        //版本号 1到2
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {

            override fun migrate(database: SupportSQLiteDatabase) {
                Log.i("zm1234", "migrate: MIGRATION_1_2")
                //没有布尔值，用INTEGER代替
                //使用"ALTER TABLE Word  ADD COLUMN bar_data2 INTEGER  NOT NULL DEFAULT 1"出错。
                //使用下面分开的形式,可以正确执行
                database.execSQL("ALTER TABLE user ADD COLUMN phone TEXT")
            }
        }

        //版本号 2到3
        val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                //没有布尔值，用INTEGER代替
                //使用"ALTER TABLE Word  ADD COLUMN bar_data2 INTEGER  NOT NULL DEFAULT 1"出错。
                //使用下面分开的形式,可以正确执行
                database.execSQL("ALTER TABLE Word ADD COLUMN bar_data2 INTEGER NOT NULL DEFAULT 1")
            }
        }
    }


}