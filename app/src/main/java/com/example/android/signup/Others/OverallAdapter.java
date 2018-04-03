package com.example.android.signup.Others;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.android.signup.Infrastructure.AdminInformation;
import com.example.android.signup.Infrastructure.UserInformation;
import com.example.android.signup.R;

import java.util.ArrayList;

/**
 * Created by Stan on 4/3/2018.
 */

public class OverallAdapter extends RecyclerView.Adapter<OverallAdapter.MyViewHolder> {
    ArrayList<AdminInformation> parentList = new ArrayList<>();
    ArrayList<UserInformation> userList = new ArrayList<>();

    OverallChildAdapter adapter;
    Context context;

    public OverallAdapter( Context context, ArrayList<AdminInformation> parentList, ArrayList<UserInformation> userList) {
        this.context=context;
        this.parentList = parentList;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lv = LayoutInflater.from(context).inflate(R.layout.tree_parent, parent, false);
        return new MyViewHolder(lv);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.parentName.setText(parentList.get(position).getLocality());
        holder.root_adminName.setText(parentList.get(position).getName());
        adapter = new OverallChildAdapter(context, getFilteredUserList(position));
        holder.clv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return parentList.size();
    }
    public ArrayList<UserInformation> getFilteredUserList(int pos) {
        ArrayList<UserInformation> users=new ArrayList<>();
        for(int i=0;i<userList.size();i++) {
            if (parentList.get(pos).getLocality().compareTo(userList.get(i).getLocality())==0){
                users.add(userList.get(i));
            }
        }
        return users;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout cLL;
        TextView parentName;
        TextView root_adminName;
        RecyclerView clv;
        public MyViewHolder(View itemView) {
            super(itemView);
            root_adminName = itemView.findViewById(R.id.root_admin_name);
            cLL=itemView.findViewById(R.id.child_node);
            parentName=itemView.findViewById(R.id.tree_text);
             clv = itemView.findViewById(R.id.parent_rv);
            clv.setLayoutManager(new LinearLayoutManager(context));
            cLL.setVisibility(View.GONE);
            clv.setVisibility(View.GONE);
            itemView.findViewById(R.id.parent_node).setOnClickListener(this);
            cLL.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(v.getId()==R.id.child_node){
                RecyclerView rv=v.findViewById(R.id.parent_rv);
                if(rv.getVisibility()==View.GONE){
                    rv.setVisibility(View.VISIBLE);
                }
                else{
                    rv.setVisibility(View.GONE);
                }
            }
            if(v.getId()==R.id.parent_node){
                LinearLayout cLL=v.findViewById(R.id.child_node);
                if(cLL.getVisibility()==View.GONE){
                    cLL.setVisibility(View.VISIBLE);
                }
                else{
                    cLL.setVisibility(View.GONE);
                }
            }
        }
    }

}



