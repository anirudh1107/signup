package com.example.android.signup.Others;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.signup.Activities.Admin.AdminHomeActivity;
import com.example.android.signup.Infrastructure.UserAddInfo;
import com.example.android.signup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by dellpc on 12/23/2017.
 */

public class AddUserAdapter extends ArrayAdapter<UserAddInfo> implements View.OnClickListener{

    private FirebaseDatabase mDatabase;
    private DatabaseReference mGetRef;
    private DatabaseReference mPutRef;
    private FirebaseAuth mAuth;
    UserAddInfo current;
    private TextView name;
    private TextView address;
    private TextView phone;
    private Button add;
    private Button reject;
    public AddUserAdapter(@NonNull Context context, int resource, @NonNull List<UserAddInfo> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(getContext()).inflate(R.layout.user_request_card,parent,false);
        current=getItem(position);
        mDatabase=FirebaseDatabase.getInstance();
        mGetRef=mDatabase.getReference().child("UserNew");
        name=view.findViewById(R.id.user_request_name);
        address=view.findViewById(R.id.user_request_address);
        phone=view.findViewById(R.id.user_request_phone);
        add=view.findViewById(R.id.user_request_accept);
        reject=view.findViewById(R.id.user_request_reject);
        name.setText(current.getUsername());
        address.setText(current.getAddress());
        phone.setText(current.getMobile());
        add.setOnClickListener(this);
        reject.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.user_request_accept)
        {
            mPutRef=mDatabase.getReference().child("User").child(current.getKey());
            mPutRef.child("Address").setValue(current.getAddress());
            mPutRef.child("Mobile").setValue(current.getMobile());
            mPutRef.child("Username").setValue(current.getUsername());

           mGetRef.child(current.getKey()).removeValue();

        }
        if(view.getId()==R.id.user_request_reject)
        {
            mGetRef.child(current.getKey()).removeValue();
        }
    }
}
