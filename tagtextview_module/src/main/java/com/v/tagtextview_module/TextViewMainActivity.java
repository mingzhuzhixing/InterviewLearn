package com.v.tagtextview_module;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TextViewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view_main);
    }

    public void tagTextView(View view) {
        startActivity(new Intent(this, TagTextViewActivity.class));
    }

    public void htmlTextView(View view) {
        startActivity(new Intent(this, HtmlTextViewActivity.class));
    }

    public void spannableStringTextView(View view) {
        startActivity(new Intent(this, SpannableStringActivity.class));
    }
}