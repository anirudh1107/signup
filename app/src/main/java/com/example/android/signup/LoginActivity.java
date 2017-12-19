package com.example.android.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mainbutton,loginbutton;
    final int mainlogin=0,login=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mainbutton=findViewById(R.id.activity_login_mainbutton);
        loginbutton=findViewById(R.id.activity_login_loginbutton);

        mainbutton.setOnClickListener(this);
        loginbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view==mainbutton)
        {
            Intent intent=new Intent(this,LoginValidate.class);
            intent.putExtra(LoginValidate.EXTRAINT,mainlogin);
            startActivity(intent);
        }

        if(view==loginbutton)
        {
            Intent intent=new Intent(this,LoginValidate.class);
            intent.putExtra(LoginValidate.EXTRAINT,login);
            startActivity(intent);
        }
    }
}
