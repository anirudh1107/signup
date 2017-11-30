package com.example.android.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {

    EditText newName;
    EditText newAdd;
    EditText newMob;
    Button newSub;
    FirebaseDatabase database;
    DatabaseReference myRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        newName=findViewById(R.id.reg_name);
        newAdd=findViewById(R.id.reg_add);
        newMob=findViewById(R.id.reg_mobile);
        newSub=findViewById(R.id.reg_submit);
        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        myRef=database.getReference("User1");
        //myanmarfna
        newSub.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
               myRef.child(mAuth.getCurrentUser().getUid()).setValue("kjklgj");
            }
        });
    }
}
