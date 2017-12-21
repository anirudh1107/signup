package com.example.android.signup.Activities.MainAdmin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.android.signup.Activities.BaseAuthenticatedActivity;
import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class HomeActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private String username,password;
    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressDialog=new ProgressDialog(this);
        mAuth= FirebaseAuth.getInstance();
        mDatabase=FirebaseDatabase.getInstance();
        button=findViewById(R.id.home_activity_adminaddrequest_button);
        button.setOnClickListener(this);
     //   mRef=mDatabase.getReference("Admin");

        //add user
       // addUser();
    }

    public static String randomAlphaNumeric(int count) {

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();

    }
    private void addUser() {
         password="12345678";
         username="Mike@gmail.com";
        progressDialog.setMessage("Registering...");
        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    setUserToDatabase();
                    Log.e("yess","LOL");
                }
                else
                {

                    progressDialog.dismiss();
                }
            }
        });
    }

    private void setUserToDatabase() {
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user=mAuth.getCurrentUser();
                    String uid=user.getUid();
                    mRef.child(uid).child("Email").setValue(username);
                    progressDialog.dismiss();
                    //TODO: send notifications to a particular admin about Email n Password
                    //TODO: send email to a particular admin about email n password
                }
                else
                {

                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        int viewID=view.getId();
        if(viewID==R.id.home_activity_adminaddrequest_button)
        {
            startActivity(new Intent(this,AddAdminActivity.class));
        }
    }
}
