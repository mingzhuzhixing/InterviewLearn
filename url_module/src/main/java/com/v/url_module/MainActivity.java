package com.v.url_module;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.v.url_module.like_view.KsgLikeView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "http://zaiadev.laidan.com/p/newbee/newbornZone?back=1234";

        Uri uri = Uri.parse(url);
        String content = uri.getQueryParameter("back");
        TextView textView = findViewById(R.id.tv_content);
        textView.setText(content);



        KsgLikeView ksgLikeView = findViewById(R.id.live_view);
        ksgLikeView.addLikeImages(
                R.mipmap.ic_live_yellow_heart, R.mipmap.ic_live_smile_face_heart, R.mipmap.ic_live_red_heart,
                R.mipmap.ic_live_thumbs_up_yellow, R.mipmap.ic_live_smile_face, R.mipmap.ic_live_orange_heart,
                R.mipmap.ic_live_grin_face_eyes, R.mipmap.ic_live_smile_face_sunglas);

        findViewById(R.id.btn_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.i("ImageView", "------>" + imageView.hashCode());
                ksgLikeView.addFavor();
            }
        });
    }
}