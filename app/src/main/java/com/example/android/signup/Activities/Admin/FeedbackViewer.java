package com.example.android.signup.Activities.Admin;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.signup.Infrastructure.AdminInformation;
import com.example.android.signup.Infrastructure.FeedbackInfo;
import com.example.android.signup.Others.AddAdminAdapter;
import com.example.android.signup.Others.FeedbackAdapter;
import com.example.android.signup.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FeedbackViewer extends AppCompatActivity {
    private ListView listView;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private FeedbackAdapter adapter;
    private List<FeedbackInfo> list;
    private Button button;
    private SharedPreferences sharedPreferences;
    private String loc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_viewer);
        sharedPreferences=getSharedPreferences("locality", Context.MODE_PRIVATE);
        loc=sharedPreferences.getString("Loc","");
        list=new ArrayList<>();
        listView=findViewById(R.id.feedback_list);
        adapter=new FeedbackAdapter(this,R.layout.feedback_card,list);

        listView.setAdapter(adapter);

        getData();

    }

    private void getData()
    {
        mDatabase=FirebaseDatabase.getInstance();
        mRef=mDatabase.getReference("UserFeedback");
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FeedbackInfo feedbackInfo=dataSnapshot.getValue(FeedbackInfo.class);
                Log.d("ta",feedbackInfo.getLocality());
                if(loc.equalsIgnoreCase(feedbackInfo.getLocality()))
                {

                    adapter.add(feedbackInfo);

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
