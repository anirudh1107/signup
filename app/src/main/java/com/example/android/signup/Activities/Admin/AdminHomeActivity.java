package com.example.android.signup.Activities.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.android.signup.Activities.BaseAuthenticatedActivity;
import com.example.android.signup.Infrastructure.AdminProf;
import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminHomeActivity extends BaseAuthenticatedActivity {

    LinearLayout sign;
    LinearLayout getComplaint;
    String email;
    String password;
    ProgressDialog dialog;
    private LinearLayout addUser;
    private LinearLayout passwordchange;
    private FirebaseDatabase mDatabse;
    private DatabaseReference mRef;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        sign=(LinearLayout)findViewById(R.id.signup);
        addUser=findViewById(R.id.new_user);
        passwordchange=findViewById(R.id.password_change);
        sharedPreferences=getSharedPreferences("locality", Context.MODE_PRIVATE);
        dialog=new ProgressDialog(this);
        dialog.setMessage("wait");
        dialog.show();
        mAuth=FirebaseAuth.getInstance();
        mDatabse=FirebaseDatabase.getInstance();
        mRef=mDatabse.getReference().child("Admin");
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AdminProf current=dataSnapshot.getValue(AdminProf.class);
                if(mAuth.getCurrentUser().getUid().equalsIgnoreCase(dataSnapshot.getKey()))
                {
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("Loc",current.getLocality());

                    editor.commit();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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

        passwordchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomeActivity.this,PasswordChange.class));
            }
        });
    }
}
