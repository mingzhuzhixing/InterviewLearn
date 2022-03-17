package com.v.module_telephony;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class TelephonyManagerActivity extends AppCompatActivity {
    TextView tvInfo;
    String[] permissions = {Manifest.permission.READ_SMS, Manifest.permission.READ_PHONE_NUMBERS, Manifest.permission.READ_PHONE_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephony_manager);
        tvInfo = findViewById(R.id.tv_info);

        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfo();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    private void getInfo() {
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        //返回手机号码，对于GSM网络来说即MSISDN
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                this.requestPermissions(permissions, 100);
            }
            return;
        }
        String line1Number = tm.getLine1Number();

        //返回ISO标准的国家码，即国际长途区号
        String networkCountryIso = tm.getNetworkCountryIso();

        //返回MCC+MNC代码 (SIM卡运营商国家代码和运营商网络代码)(IMSI)
        String networkOperator = tm.getNetworkOperator();

        //返回移动网络运营商的名字(SPN)
        String networkOperatorName = tm.getNetworkOperatorName();

        /**
         * 返回移动终端的类型
         *
         * PHONE_TYPE_CDMA 手机制式为CDMA，电信
         * PHONE_TYPE_GSM 手机制式为GSM，移动和联通
         * PHONE_TYPE_NONE 手机制式未知
         */
        int phoneType = tm.getPhoneType();

        int networkType = tm.getNetworkType();

        //返回SIM卡提供商的国家代码
        String simCountryIso = tm.getSimCountryIso();

        String simOperator = tm.getSimOperator();

        Log.i("Telephony", "line1Number:" + line1Number + "\nnetworkCountryIso:" + networkCountryIso +
                "\nnetworkOperator:" + networkOperator + "\nnetworkOperatorName:" + networkOperatorName +
                "\nphoneType:" + phoneType + "\nnetworkType:" + networkType + "\nsimCountryIso:" + simCountryIso + "\nsimOperator:" + simOperator);
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("line1Number:").append(line1Number).append("\n");
        stringBuffer.append("networkCountryIso:").append(networkCountryIso).append("\n");

        tvInfo.setText(stringBuffer.toString());

    }
}