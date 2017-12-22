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

    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private Button button;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        progressDialog=new ProgressDialog(this);

        mDatabase=FirebaseDatabase.getInstance();
        button=findViewById(R.id.home_activity_adminaddrequest_button);
        button.setOnClickListener(this);
     //   mRef=mDatabase.getReference("Admin");

        //add user
       // addUser();
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
