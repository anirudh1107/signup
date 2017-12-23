package com.example.android.signup.Activities.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.android.signup.Activities.BaseAuthenticatedActivity;
import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHomeActivity extends BaseAuthenticatedActivity {

    LinearLayout sign;
    LinearLayout getComplaint;
    String email;
    String password;
    ProgressDialog dialog;
    private LinearLayout addUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        sign=(LinearLayout)findViewById(R.id.signup);
        addUser=findViewById(R.id.new_user);
        dialog=new ProgressDialog(this);
        dialog.setMessage("wait");
        dialog.show();
        mAuth=FirebaseAuth.getInstance();

        dialog.dismiss();

        sign.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view) {

                                        Intent i=new Intent(AdminHomeActivity.this,AdminAddUserActivity.class);
                                        startActivity(i);
                                    }
                                }
        );


        getComplaint=(LinearLayout) findViewById(R.id.get_complain);
        getComplaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AdminHomeActivity.this,ComplainListenerActivity.class);
                startActivity(i);
            }
        });

        addUser.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomeActivity.this,user_for_register.class));
            }
        });
    }
}
