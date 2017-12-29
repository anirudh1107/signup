package com.example.android.signup.Activities.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.android.signup.Activities.BaseAuthenticatedActivity;
import com.example.android.signup.Infrastructure.UserAddInfo;
import com.example.android.signup.Others.AddUserAdapter;
import com.example.android.signup.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class user_for_register extends BaseAuthenticatedActivity {

    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabse;
    private DatabaseReference mRef;
    private List<UserAddInfo> list1;
    AddUserAdapter adapter;
    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_for_register);

        mDatabse=FirebaseDatabase.getInstance();
        mRef=mDatabse.getReference().child("UserNew");
        listView1=findViewById(R.id.list_view2);
        list1=new ArrayList<UserAddInfo>();

        adapter=new AddUserAdapter(this,R.layout.user_request_card,list1);
        listView1.setAdapter(adapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserAddInfo current=dataSnapshot.getValue(UserAddInfo.class);
                String key=dataSnapshot.getKey();
                current.setKey(key);
                adapter.add(current);
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
