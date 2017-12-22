package com.example.android.signup.Others;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.signup.Infrastructure.AdminInformation;
import com.example.android.signup.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;


public class AddAdminAdapter extends ArrayAdapter<AdminInformation> implements View.OnClickListener {
    private TextView name;
    private TextView email;
    private TextView mobileNumber;
    private LayoutInflater inflater;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Button button;
    private String username,password;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    int pos;
    public AddAdminAdapter(@NonNull Context context, int resource, @NonNull List<AdminInformation> objects) {
        super(context, resource, objects);
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view =inflater.inflate(R.layout.admin_request_card,parent,false);
        AdminInformation information=getItem(position);
         pos=position;
        mDatabase=FirebaseDatabase.getInstance();
        button=view.findViewById(R.id.card_adduser_button);
        name=view.findViewById(R.id.card_name);
        email=view.findViewById(R.id.card_email);
        mobileNumber=view.findViewById(R.id.card_phone);
        name.setText(information.getName());
        email.setText(information.getEmail());
        mobileNumber.setText(information.getMobile());
        button.setOnClickListener(this);
        button.setTag(position);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.card_adduser_button)
        {
            AdminInformation information=getItem((Integer) view.getTag());
            mRef=mDatabase.getReference("AdminSignUpRequest").child(information.getKey());
            mRef.child("status").setValue(1);
            mRef.removeValue();
            //TODO:Generate password and notify to admin
            mRef=mDatabase.getReference("Admin");
            progressDialog=new ProgressDialog(getContext());
            progressDialog.setMessage("Adding Admin...");
            progressDialog.show();
            //add admin
            addAdmin(email.getText().toString(),randomAlphaNumeric(8));
            Toast.makeText(getContext(),"Admin Added",Toast.LENGTH_SHORT).show();
            remove(information);
            notifyDataSetChanged();

        }
    }
    public static String randomAlphaNumeric(int count) {

        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();

    }
    private void addAdmin(String username,String password) {
        this.username=username;


        password="12345678";


        this.password=password;

        mAuth= FirebaseAuth.getInstance();


        mAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    progressDialog.dismiss();
                    setUserToDatabase();
                }
                else
                {

                    progressDialog.dismiss();
                }
            }
        });
    }

    private void setUserToDatabase() {
        mAuth.signInWithEmailAndPassword(username,password).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
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
}

