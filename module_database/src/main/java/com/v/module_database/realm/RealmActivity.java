package com.v.module_database.realm;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.v.module_base.BaseTitleBarActivity;
import com.v.module_database.R;
import com.v.module_database.realm.dao.UserDao;
import com.v.module_database.realm.dao.UserDaoImpl;
import com.v.module_database.realm.model.User;

import java.util.List;

/**
 * realm 可视化工具（Realm Studio）
 * https://docs.realm.io/sync/realm-studio/
 * <p>
 * https://code84.com/733439.html
 */
@SuppressLint("SetTextI18n")
public class RealmActivity extends BaseTitleBarActivity {

    TextView tvContent;
    EditText etUserId;
    EditText etUserName;
    EditText etUserAge;

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
        etUserId = findViewById(R.id.et_user_id);
        etUserName = findViewById(R.id.et_user_name);
        etUserAge = findViewById(R.id.et_user_age);
    }

    /**
     * 增加
     */
    public void insertData(View view) {
        try {
            String userId = etUserId.getText().toString().trim();
            String userName = etUserName.getText().toString().trim();
            String userAge = etUserAge.getText().toString().trim();
            Log.i("zm1234", "insertData: userId:" + userId + " userName:" + userName);
            if (TextUtils.isEmpty(userId) || TextUtils.isEmpty(userName)) {
                Toast.makeText(this, "请输入昵称或id", Toast.LENGTH_SHORT).show();
                return;
            }
            int age = !TextUtils.isEmpty(userAge) ? Integer.parseInt(userAge) : 20;
            User user = new User(userId, userName, age);
            UserDao dao = new UserDaoImpl(this);
            dao.insert(user);
            tvContent.setText("插入成功：" + user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改
     */
    public void updateData(View view) {
        try {
            String userId = etUserId.getText().toString().trim();
            String userName = etUserName.getText().toString().trim();
            String userAge = etUserAge.getText().toString().trim();

            int age = !TextUtils.isEmpty(userAge) ? Integer.parseInt(userAge) : 20;

            UserDao dao = new UserDaoImpl(this);
            dao.updateUserByUserId(userId, userName, age);
            tvContent.setText("修改成功 userId:" + userId + " userName:" + userName + " age:" + age);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除
     */
    public void deleteData(View view) {
        try {
            String userId = "1";
            UserDao dao = new UserDaoImpl(this);
            dao.deleteUser(userId);
            tvContent.setText("删除成功：user_id:" + userId);
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
            dao.closeDB();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}