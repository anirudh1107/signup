package com.example.android.signup.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends BaseCompactActivity {

    LinearLayout sign;
    LinearLayout getComplaint;
    String email;
    String password;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        sign=(LinearLayout)findViewById(R.id.signup);
        dialog=new ProgressDialog(this);
        dialog.setMessage("wait");
        dialog.show();
        mAuth=FirebaseAuth.getInstance();
        email="admin@admin.com";
        password="12345678";


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(HomeActivity.this,"ready",Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(HomeActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();

                        }

                    }
                });

        sign.setOnClickListener(new View.OnClickListener()
                                {
                                    @Override
                                    public void onClick(View view) {

                                        Intent i=new Intent(HomeActivity.this,MainActivity.class);
                                        startActivity(i);
                                    }
                                }
        );


        getComplaint=(LinearLayout) findViewById(R.id.get_complain);
        getComplaint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i=new Intent(HomeActivity.this,ComplainListenerActivity.class);
                startActivity(i);
            }
        });
    }
}
