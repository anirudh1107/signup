package com.example.android.signup.Others;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.signup.Infrastructure.AdminInformation;
import com.example.android.signup.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class AddAdminAdapter extends ArrayAdapter<AdminInformation> implements View.OnClickListener {
    private TextView name;
    private TextView email;
    private TextView mobileNumber;
    private LayoutInflater inflater;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Button button;
    private int flag=1;
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
            Toast.makeText(getContext(),"User Added",Toast.LENGTH_SHORT).show();

            remove(information);
            notifyDataSetChanged();

        }
    }
}

