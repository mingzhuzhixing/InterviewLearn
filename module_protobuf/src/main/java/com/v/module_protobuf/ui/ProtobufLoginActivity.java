package com.v.module_protobuf.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.v.module_protobuf.LoginRequestOuterClass;
import com.v.module_protobuf.R;
import com.v.module_protobuf.http.HttpSend;
import com.v.module_protobuf.http.ResultCallbackListener;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;

public class ProtobufLoginActivity extends AppCompatActivity {
    private EditText etUsername;
    private EditText etPwd;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protobuf_login);
        HttpSend.getInstance().initContext(this);

        etUsername = findViewById(R.id.et_username);
        etPwd = findViewById(R.id.et_pwd);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("登录中，请稍等");
    }

    /**
     * 登录(HttpURLConnection+Protobuf)
     */
    public void onLoginURLConnection(View view) {
        progressDialog.show();
        progressDialog.show();//显示加载框
        //获取用户输入的用户名和密码
        String username = etUsername.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        //构造LoginRequest对象
        //LoginRequestOuterClass.LoginRequest loginRequest = LoginRequestOuterClass.LoginRequest.newBuilder().setUsername(username).setPwd(pwd).build();
        //登陆
        login("http://192.168.1.3:8080/protobuf_webtest/login.action", "username=" + username + "&pwd=" + pwd);
    }

    /**
     * 开始登录（基于HttpURLConnection）
     */
    public void login(String baseUrl, String params) {
        new Thread() {
            @Override
            public void run() {
                DataOutputStream out = null;
                try {
                    URL url = new URL(baseUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    // http正文内，因此需要设为true
                    conn.setDoOutput(true);
                    // 默认是 GET方式
                    conn.setRequestMethod("POST");
                    // 意思是正文是urlencoded编码过的form参数
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    out = new DataOutputStream(conn.getOutputStream());
                    // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
                    out.writeBytes(params);
                    //流用完记得关
                    out.flush();
                    if (conn.getResponseCode() == 200) {
                        //解析结果
                        final LoginRequestOuterClass.LoginResponse loginResponse = LoginRequestOuterClass.LoginResponse.parseFrom(conn.getInputStream());
                        Log.e("ProtobufLoginActivity", "登陆结果：code = " + loginResponse.getCode() + "\tmsg = " + loginResponse.getMsg());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(ProtobufLoginActivity.this, loginResponse.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (out != null) {
                            out.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();
                    }
                });
            }
        }.start();
    }

    /**
     * 登录(Retrofit+Protobuf)
     */
    public void onLoginRetrofit(View view) {
        //获取用户输入的用户名和密码
        String username = etUsername.getText().toString().trim();
        String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.show();
        //开始请求
        HttpSend.getInstance().login(username, pwd, new ResultCallbackListener<LoginRequestOuterClass.LoginResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(LoginRequestOuterClass.LoginResponse value) {
                Log.e("ProtobufLoginActivity", "登陆结果：code = " + value.getCode() + "\tmsg = " + value.getMsg());
                Toast.makeText(ProtobufLoginActivity.this, value.getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Log.e("ProtobufLoginActivity", "e-->" + e.getMessage());
            }

            @Override
            public void onComplete() {
                progressDialog.dismiss();
            }
        });
    }
}