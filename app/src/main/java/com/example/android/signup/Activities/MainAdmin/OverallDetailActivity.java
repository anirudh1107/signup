package com.example.android.signup.Activities.MainAdmin;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.android.signup.Activities.BaseActivity;
import com.example.android.signup.Infrastructure.AdminInformation;
import com.example.android.signup.Infrastructure.UserInformation;
import com.example.android.signup.Others.OverallAdapter;
import com.example.android.signup.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

/**
 * Created by Stan on 4/1/2018.
 */

public class OverallDetailActivity extends BaseActivity{
    private RecyclerView lv;
    private OverallAdapter adapter;
     ArrayList<AdminInformation> admins=new ArrayList<>();
    ArrayList<UserInformation> userList=new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overall_detail);
        lv=findViewById(R.id.overall_lv);
        lv.setLayoutManager(new LinearLayoutManager(this));
         adapter=new OverallAdapter(this,getAdmins(),getUsers());

        lv.setAdapter(adapter);
    }
    public ArrayList<UserInformation> getUsers() {

        FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
        mDatabase.getReference().child("User").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInformation userInfo=dataSnapshot.getValue(UserInformation.class);
                userList.add(userInfo);
                adapter.notifyDataSetChanged();
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
        return userList;
    }
    public ArrayList<AdminInformation> getAdmins() {

        FirebaseDatabase mDatabase=FirebaseDatabase.getInstance();
        mDatabase.getReference().child("Admin").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                AdminInformation adminInfo=dataSnapshot.getValue(AdminInformation.class);
                admins.add(adminInfo);
                adapter=new OverallAdapter(getApplicationContext(),admins,userList);
                lv.setAdapter(adapter);
               adapter.notifyDataSetChanged();
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

        return admins;
    }


}


//       // public class CustomComparatorAdmin implements Comparator<AdminInformation> {
//
//            @Override
//            public int compare(AdminInformation o1, AdminInformation o2) {
//                return o1.getLocality().compareTo(o2.getLocality());
//            }
//        }
//        public class CustomComparatorUser implements Comparator<UserInformation> {
//
//            @Override
//            public int compare(UserInformation o1, UserInformation o2) {
//                return o1.getLocality().compareTo(o2.getLocality());
//            }
//        }
//
//        protected Void doInBackground(Void... voids) {
////      //      adinfo = getAdmins();
////          //  usinfo = getUsers();
////            Log.e("t","t3");
////            Collections.sort(adinfo,new CustomComparatorAdmin());
////  //          Collections.sort(usinfo,new CustomComparatorUser());
////            root = TreeNode.root();
//            return null;
//        }





