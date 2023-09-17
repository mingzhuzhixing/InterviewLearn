package com.v.module_database;

import android.content.Intent;
import android.view.View;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_database.realm.RealmActivity;
import com.v.module_database.sqlite_custom.SqliteCustomActivity;

public class DatabaseMainActivity extends BaseTitleBarActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_database_main;
    }

    @Override
    protected String setTitle() {
        return "数据库";
    }

    public void sqliteCustomClick(View view) {
        startActivity(new Intent(this, SqliteCustomActivity.class));
    }

    public void realmClick(View view) {
        startActivity(new Intent(this, RealmActivity.class));
    }
}
