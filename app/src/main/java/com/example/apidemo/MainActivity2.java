package com.example.apidemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apidemo.dto.itemModel;

public class MainActivity2 extends AppCompatActivity {
    TextView id, name, email, avt_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        id = findViewById(R.id.Id);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        avt_url = findViewById(R.id.avt_url);

        String Id = getIntent().getExtras().getString("id");
        String Name = getIntent().getExtras().getString("name");
        String Email = getIntent().getExtras().getString("email");
        String Avt_url = getIntent().getExtras().getString("avt_url");

        id.setText("ID: " + Id);
        name.setText("name" + Name);
        email.setText("email" + Email);
        avt_url.setText("avtar image" + Avt_url);

    }
}