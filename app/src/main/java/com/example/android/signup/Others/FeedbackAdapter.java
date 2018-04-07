package com.example.android.signup.Others;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.android.signup.Infrastructure.AdminInformation;
import com.example.android.signup.Infrastructure.FeedbackInfo;
import com.example.android.signup.R;

import java.util.List;

/**
 * Created by utkarshh12 on 4/7/2018.
 */

public class FeedbackAdapter extends ArrayAdapter<FeedbackInfo> implements View.OnClickListener {

    private LayoutInflater inflater;
    private CardView cardView;
    private TextView user_id,desc;
    private FrameLayout frameLayout;
    public FeedbackAdapter(@NonNull Context context, int resource, @NonNull List<FeedbackInfo> objects) {
        super(context, resource,objects);
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view=inflater.inflate(R.layout.feedback_card,parent,false);
        FeedbackInfo feedbackInfo=getItem(position);

        cardView=view.findViewById(R.id.feedback_card);

        user_id=view.findViewById(R.id.user_id);
        desc=view.findViewById(R.id.card_desc);

        user_id.setText(feedbackInfo.getUid());

        desc.setText(feedbackInfo.getDetail());
        Log.e("LOLO",feedbackInfo.getDetail());

        cardView.setOnClickListener(this);


        return view;


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.feedback_card)
        {
            frameLayout=v.findViewById(R.id.frame_feedback);
            if(frameLayout.getVisibility()==View.VISIBLE)
            {
                frameLayout.setVisibility(View.GONE);
            }
            else
            {
                frameLayout.setVisibility(View.VISIBLE);
            }
        }
    }
}
