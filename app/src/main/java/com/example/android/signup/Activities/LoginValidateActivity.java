package com.example.android.signup.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.signup.Infrastructure.AdminInformation;
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
import com.google.firebase.database.ValueEventListener;

public class LoginValidateActivity extends BaseActivity implements View.OnClickListener {
public static final String EXTRAINT="EXTRAINT";
    private static final int REQUEST_SIGNUP = 121;
    private Button signup;
    private TextView or;
    private View progressBar;
    protected EditText userName, password;
    private int status;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private ChildEventListener mChildEventListener;
    private AdminInformation info;
    private static final int RESULT_WAIT = 120;
    private TextInputLayout usernameWrapper;
    private TextInputLayout passwordWrapper;

    @Override
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_validate);

        userName =  findViewById(R.id.activity_login_validate_username);
        password = findViewById(R.id.activity_login_validate_password);
        usernameWrapper=findViewById(R.id.activity_login_validate_username_wrapper);
        passwordWrapper=findViewById(R.id.activity_login_validate_password_wrapper);
        usernameWrapper.setHint("USERNAME");
        passwordWrapper.setHint("PASSWORD");
        passwordWrapper.setPasswordVisibilityToggleEnabled(true);
        progressBar=findViewById(R.id.login_activity_progressbar);
        progressBar.setVisibility(View.GONE);
        mAuth=FirebaseAuth.getInstance();

        signup=findViewById(R.id.activity_login_validate_signup);
        signup.setOnClickListener(this);
        or=findViewById(R.id.or);
        status=getIntent().getIntExtra(EXTRAINT,0);

        if(status==0)
        {
            signup.setVisibility(View.GONE);
            or.setVisibility(View.GONE);
        }

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //Toast.makeText(getApplicationContext(), "Logged In As " + user.getEmail(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getApplicationContext(), "User Not Logged In", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
        if(view==signup)
        {
            Intent intent=new Intent(this,SignUpActivity.class);
            startActivityForResult(intent,REQUEST_SIGNUP);
            setResult(RESULT_WAIT);
            finish();
        }
    }
    public void login(View v) {
        if(status==0){
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(userName.getText().toString(), password.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        checkMainAdminValidity(userName.getText().toString(), password.getText().toString());
                        progressBar.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"No internet connection",Toast.LENGTH_LONG).show();
                        progressBar.setVisibility(View.GONE);
                    }
                }
            });

            return;}
        if(userName.getText().toString().length()==0||password.getText().toString().length()==0)
        {
            Toast.makeText(this,"Username or Password is Empty",Toast.LENGTH_SHORT).show();

        }
        if(status==1) {
            progressBar.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(userName.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()) {
                                Toast.makeText(LoginValidateActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            } else {

                                if(status==1)
                                {
                                    checkAdminValidity(mAuth.getCurrentUser().getUid());
                                }


                            }
                        }
                    });
        }
    }

    private void checkMainAdminValidity(String username, String password) {
        if(username.compareTo("mainadmin@gmail.com")==0&&password.compareTo("12345678")==0){
            setResult(RESULT_OK);
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Incorrect username or password",Toast.LENGTH_LONG).show();
        }
    }

    private void checkAdminValidity(final String uid) {
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("Admin");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(uid))
                {

                    Log.e("FLAG","ONE");
                    Toast.makeText(LoginValidateActivity.this, "Login successfull.",
                            Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                }
                else
                {
                    Log.e("FLAG","ZERO");
                    Toast.makeText(LoginValidateActivity.this, "Administrator Permission Required",
                            Toast.LENGTH_SHORT).show();
                    setResult(RESULT_CANCELED);
                    finish();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_SIGNUP)
        {
            if(resultCode==RESULT_OK)
            {
                setResult(RESULT_WAIT);
                finish();
            }
            else
            {
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }
}

