package com.qtcteam.loginmvc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qtcteam.loginmvc.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void submit(View v) {
        // TODO
    }

    public void back(View v) {
        finish();
    }

}
