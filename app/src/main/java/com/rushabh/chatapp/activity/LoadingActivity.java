package com.rushabh.chatapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rushabh.chatapp.R;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);




//        At last
        startActivity(new Intent(LoadingActivity.this,LogInActivity.class));
    }
}
