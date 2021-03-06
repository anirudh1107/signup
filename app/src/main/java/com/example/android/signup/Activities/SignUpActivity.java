package com.example.android.signup.Activities;

import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends BaseActivity implements View.OnClickListener {
    private static final int RESULT_WAIT = 120;
    private EditText name;
    private EditText email;
    private EditText mobile;
    private Spinner locality;
    private Button button;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private TextInputLayout nameWrapper;
    private TextInputLayout emailWrapper;
    private TextInputLayout phoneWrapper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initialize();
        nameWrapper.setHint("NAME");
        emailWrapper.setHint("EMAIL ID");
        phoneWrapper.setHint("PHONE");
        button.setOnClickListener(this);
    }
    private void initialize() {
        name=findViewById(R.id.signup_name);
        email=findViewById(R.id.signup_email);
        mobile=findViewById(R.id.signup_mobile);
        button=findViewById(R.id.signup_button);
        locality=findViewById(R.id.signup_locality);
        nameWrapper=findViewById(R.id.signup_name_wrapper);
        emailWrapper=findViewById(R.id.signup_email_wrapper);
        phoneWrapper=findViewById(R.id.signup_mobile_wrapper);
    }
    @Override
    public void onClick(View view) {




        if(name.getText().toString().isEmpty())
            Toast.makeText(this,"name block is empty",Toast.LENGTH_SHORT).show();
        else if(email.getText().toString().isEmpty())
            Toast.makeText(this,"email should not be empty",Toast.LENGTH_SHORT).show();
        else if(mobile.getText().toString().isEmpty())
            Toast.makeText(this,"mobile should not be empty",Toast.LENGTH_SHORT).show();
        else if(String.valueOf(locality.getSelectedItem()).equals("NONE"))
            Toast.makeText(this,"Please select the locality",Toast.LENGTH_SHORT).show();
        else
        {

            mDatabase=FirebaseDatabase.getInstance();
            mRef = mDatabase.getReference("AdminSignUpRequest").push();
            mRef.child("name").setValue(name.getText().toString());
            mRef.child("email").setValue(email.getText().toString());
            mRef.child("mobile").setValue(mobile.getText().toString());
            mRef.child("Locality").setValue(String.valueOf(locality.getSelectedItem()));
            mRef.child("status").setValue(0);
        }


    }
}
