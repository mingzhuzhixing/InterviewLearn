package com.v.module_protobuf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProtobufMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protobuf_main);

        PersonProto.Person.Builder builder = PersonProto.Person.newBuilder();
        builder.setName("Tom")
                .setId(111).setBoo(false)
                .setEmail("123@123.com")
                .setPhone("123456789");
        PersonProto.Person person = builder.build();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PersonProto.Person parseFrom = PersonProto.Person.parseFrom(person.toByteArray());
                    ((TextView) findViewById(R.id.tv_text)).setText(parseFrom.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}