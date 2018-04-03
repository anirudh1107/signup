package com.example.android.signup.Others;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.signup.Infrastructure.UserInformation;
import com.example.android.signup.R;

import java.util.ArrayList;

/**
 * Created by Stan on 4/3/2018.
 */

public class OverallChildAdapter extends RecyclerView.Adapter<OverallChildAdapter.MyViewHolder> {

    ArrayList<UserInformation> users=new ArrayList<>();
    LayoutInflater inflater;
    public OverallChildAdapter(Context context,ArrayList<UserInformation> users) {
        this.users = users;
        this.inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.tree_child,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView userName;
        public MyViewHolder(View itemView) {
            super(itemView);
            userName=itemView.findViewById(R.id.child_username);
        }
    }
}
