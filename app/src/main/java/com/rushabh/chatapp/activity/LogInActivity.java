package com.rushabh.chatapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rushabh.chatapp.MainActivity;
import com.rushabh.chatapp.R;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



//        At last
        startActivity(new Intent(LogInActivity.this,MainActivity.class));
    }
}
