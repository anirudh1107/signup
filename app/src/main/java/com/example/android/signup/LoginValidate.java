package com.example.android.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginValidate extends AppCompatActivity implements View.OnClickListener {
public static final String EXTRAINT="EXTRAINT";
private Button signup;
private TextView or;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_validate);

        signup=findViewById(R.id.activity_login_validate_signup);
        signup.setOnClickListener(this);
        or=findViewById(R.id.or);
        int status=getIntent().getIntExtra(EXTRAINT,0);

        if(status==0)
        {
            signup.setVisibility(View.INVISIBLE);
            or.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onClick(View view) {
        if(view==signup)
        {
            Intent intent=new Intent(this,SignUp.class);
            startActivity(intent);
        }
    }
}
