package com.example.android.signup.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.signup.Activities.Admin.AdminHomeActivity;
import com.example.android.signup.Activities.MainAdmin.HomeActivity;
import com.example.android.signup.R;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final int MAIN_ADMIN_LOGIN_CODE = 50;
    private static final int ADMIN_LOGIN_CODE = 51;
    private final int RESULT_WAIT=120;
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

            Intent intent=new Intent(this,LoginValidateActivity.class);
            intent.putExtra(LoginValidateActivity.EXTRAINT,mainlogin);
            startActivityForResult(intent,MAIN_ADMIN_LOGIN_CODE);
        }

        if(view==loginbutton)
        {
            Intent intent=new Intent(this,LoginValidateActivity.class);
            intent.putExtra(LoginValidateActivity.EXTRAINT,login);
            startActivityForResult(intent,ADMIN_LOGIN_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode == MAIN_ADMIN_LOGIN_CODE) {
                if(resultCode==RESULT_OK){
                startActivity(new Intent(this, HomeActivity.class));
                finish();}

            }
            if (requestCode==ADMIN_LOGIN_CODE)
            {
                Toast.makeText(this,"gR*",Toast.LENGTH_SHORT).show();
                if(resultCode==RESULT_OK) {

                    startActivity(new Intent(this, AdminHomeActivity.class));
                    finish();
                }
                if(resultCode==RESULT_WAIT)
                {
                    Toast.makeText(this,"Request Sent to Main Admin",Toast.LENGTH_SHORT).show();
                }
            }




    }


    public void login(View view) {
    }
}
