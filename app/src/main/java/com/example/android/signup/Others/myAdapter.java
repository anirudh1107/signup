package com.example.android.signup.Others;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.android.signup.R;

import java.util.List;

/**
 * Created by dellpc on 11/10/2017.
 */

public class myAdapter extends ArrayAdapter<word> {

    Spinner longComplainStatus;
    public myAdapter(@NonNull Context context, int resource, @NonNull List<word> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listView=convertView;
        if(listView==null)
        {
            listView= LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
        }

        word current=getItem(position);

        TextView id=listView.findViewById(R.id.id);
        TextView complaint=listView.findViewById(R.id.complaintype);
        TextView topStatus=listView.findViewById(R.id.top_status);
        TextView longComplaintId=listView.findViewById(R.id.long_customer_id);
        TextView longComplaintType=listView.findViewById(R.id.long_complain_type);
        TextView longComplaintDisc=listView.findViewById(R.id.long_discription);
        longComplainStatus=listView.findViewById(R.id.long_status);
        TextView longNumber =listView.findViewById(R.id.long_phone_number);
        LinearLayout onTap=listView.findViewById(R.id.on_tap);
        TextView location=listView.findViewById(R.id.long_location);
        TextView subType=listView.findViewById(R.id.long_complain_subType);
        LinearLayout imageview=listView.findViewById(R.id.image_view);
        ImageView longimage=listView.findViewById(R.id.image_i);

        id.setText(current.getCid());
        complaint.setText(current.getType());
        if(current.getStatus()==0)
            topStatus.setText("Pending");
        else if(current.getStatus()==1)
            topStatus.setText("In Progress");
        else
            topStatus.setText("Completed");
        longComplaintId.setText(current.getCid());
        longComplaintType.setText(current.getType());
        subType.setText(current.getTypeDetail());
        location.setText(current.getLocation());
        longNumber.setText(current.getMobNumber());
        longComplaintDisc.setText(current.getDescription());
        setupSpinner(current);
        onTap.setVisibility(View.GONE);

        String iuid=current.getImageUID().trim();

        if(iuid.isEmpty())
        {
            imageview.setVisibility(View.GONE);
        }
        else
        {
            Glide.with(longimage.getContext())
                    .load(current.getImageUID())
                    .into(longimage);
        }

        return listView;
    }

    public void setupSpinner(word current)
    {

        ArrayAdapter spinnerAdapter=ArrayAdapter.createFromResource(getContext(),R.array.status_options,android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        longComplainStatus.setAdapter(spinnerAdapter);
        longComplainStatus.setSelection(current.getStatus());


        }
}
