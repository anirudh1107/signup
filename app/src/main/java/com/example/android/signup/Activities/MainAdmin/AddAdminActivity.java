package com.example.android.signup.Activities.MainAdmin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.signup.Activities.BaseAuthenticatedActivity;
import com.example.android.signup.Infrastructure.AdminInformation;
import com.example.android.signup.Others.AddAdminAdapter;
import com.example.android.signup.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;



public class AddAdminActivity extends BaseAuthenticatedActivity {
    private ListView listView;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private AddAdminAdapter adapter;
    private List<AdminInformation> list;
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addadmin);

        list=new ArrayList<>();
        listView=findViewById(R.id.admin_request_listview);
        adapter=new AddAdminAdapter(this,R.layout.admin_request_card,list);
        listView.setAdapter(adapter);

        getData();
    }

    private void getData() {
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("AdminSignUpRequest");
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AdminInformation information=dataSnapshot.getValue(AdminInformation.class);
                String key=dataSnapshot.getKey();
                information.setKey(key);
                if(information.getStatus()==1)
                {
                   // mRef.child(key).removeValue();
                }
                else
                {
                    adapter.add(information);

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

    }
}
