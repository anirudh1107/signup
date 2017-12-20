package com.example.android.signup.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.android.signup.R;
import com.example.android.signup.Others.myAdapter;
import com.example.android.signup.Infrastructure.word;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ComplainListenerActivity extends BaseCompactActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase mDatabase;
    DatabaseReference myRef;
    private List<word> list1;
    ListView list_View;
    private ChildEventListener mChildEventListner;
    myAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaintlistner);


        mDatabase=FirebaseDatabase.getInstance();
        myRef=mDatabase.getReference().child("Complain");

        list1=new ArrayList<word>();
        list_View=findViewById(R.id.list_view1);
        adapter=new myAdapter(this,R.layout.listitem,list1);

        list_View.setAdapter(adapter);

        mChildEventListner=new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                word newWord = dataSnapshot.getValue(word.class);
                String key=dataSnapshot.getKey();
                newWord.setKey(key);
                adapter.add(newWord);

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
        };
        myRef.addChildEventListener(mChildEventListner);



        list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                final word current = list1.get(i);
                final LinearLayout Onclick=(LinearLayout)view.findViewById(R.id.on_tap);
                if(Onclick.getVisibility()==View.GONE)
                    Onclick.setVisibility(View.VISIBLE);
                Button hide=(Button)view.findViewById(R.id.hide);
                hide.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        Onclick.setVisibility(View.GONE);
                    }
                });

                final DatabaseReference upref=mDatabase.getReference().child("Complain").child(current.getKey()).child("Status");

                Spinner mSpinner=(Spinner)view.findViewById(R.id.long_status);
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                        String selection = (String) adapterView.getItemAtPosition(i);

                        int upload;
                        if(selection.equalsIgnoreCase("Pending"))
                            upload=0;
                        else if(selection.equalsIgnoreCase("In Progress"))
                            upload=1;
                        else
                            upload=2;

                        upref.setValue(upload);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });
    }

}
