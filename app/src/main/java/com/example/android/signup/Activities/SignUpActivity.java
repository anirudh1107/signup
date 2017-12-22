package com.example.android.signup.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.security.AccessController.getContext;

public class SignUpActivity extends BaseAuthenticatedActivity implements View.OnClickListener {
    private static final int RESULT_WAIT = 120;
    private EditText name;
    private EditText email;
    private EditText mobile;
    private Button button;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name=findViewById(R.id.signup_name);
        email=findViewById(R.id.signup_email);
        mobile=findViewById(R.id.signup_mobile);
        button=findViewById(R.id.signup_button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mDatabase=FirebaseDatabase.getInstance();
        mAuth=FirebaseAuth.getInstance();

            mRef = mDatabase.getReference("AdminSignUpRequest").push();
            mRef.child("name").setValue(name.getText().toString());
            mRef.child("email").setValue(email.getText().toString());
            mRef.child("mobile").setValue(mobile.getText().toString());
            mRef.child("status").setValue(0);
            setResult(RESULT_OK);
            finish();


    }
}
