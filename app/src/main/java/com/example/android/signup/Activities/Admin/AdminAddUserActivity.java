package com.example.android.signup.Activities.Admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.signup.Activities.BaseAuthenticatedActivity;
import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
 // ho ja
public class AdminAddUserActivity extends BaseAuthenticatedActivity {

    private EditText user;
    private EditText password;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)findViewById(R.id.newuser);
        add=(Button)findViewById(R.id.add);
        password=(EditText)findViewById(R.id.pass);
        progressDialog=new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();

            }
        });
    }

    private void registerUser()
    {
        final String email=user.getText().toString().trim();
        final String pass=password.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {

            Toast.makeText(this,"please give email address",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(this,"please enter the password",Toast.LENGTH_SHORT).show();

        }
        progressDialog.setMessage("REGISTERING......");

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Toast.makeText(AdminAddUserActivity.this,"signup successful",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    openForRegistration();

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(AdminAddUserActivity.this,"Please try again later",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }
            }
        });




    }

    void openForRegistration()
    {
        final String email=user.getText().toString().trim();
        final String pass=password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i=new Intent(AdminAddUserActivity.this,DetailActivity.class);
                            startActivity(i);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(AdminAddUserActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                    }
                });
    }
}
