package com.v.module_database.realm;

import android.view.View;
import android.widget.TextView;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_database.R;
import com.v.module_database.realm.dao.UserDao;
import com.v.module_database.realm.dao.UserDaoImpl;
import com.v.module_database.realm.model.User;

import java.util.List;

public class RealmActivity extends BaseTitleBarActivity {

    TextView tvContent;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_realm;
    }

    @Override
    protected String setTitle() {
        return "Realm数据库";
    }

    @Override
    public void processLogical() {
        super.processLogical();
        tvContent = findViewById(R.id.tv_content);
    }

    /**
     * 增加
     */
    public void insertData(View view) {
        try {
            User user = new User(1, "张三", 20);
            UserDao dao = new UserDaoImpl(this);
            dao.insert(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改
     */
    public void updateData(View view) {
        try {
            User user = new User(1, "张三", 30);
            UserDao dao = new UserDaoImpl(this);
            dao.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     */
    public void deleteData(View view) {
        try {
            UserDao dao = new UserDaoImpl(this);
            dao.deleteUser("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取所有
     */
    public void getAllData(View view) {
        try {
            UserDao dao = new UserDaoImpl(this);
            List<User> allUser = dao.getAllUser();
            tvContent.setText(allUser.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}