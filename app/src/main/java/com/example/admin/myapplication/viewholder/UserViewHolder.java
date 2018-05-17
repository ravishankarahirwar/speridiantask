package com.example.admin.myapplication.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.admin.myapplication.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
    public LinearLayout mItemLayour;
    public TextView mUserId;
    public TextView mUserFirstName;
    public TextView mUserLastName;

    public UserViewHolder(View itemView) {
        super(itemView);
        mItemLayour = itemView.findViewById(R.id.item_layout);
        mUserId = itemView.findViewById(R.id.user_id);
        mUserFirstName = itemView.findViewById(R.id.user_fname);
        mUserLastName = itemView.findViewById(R.id.user_lname);
    }

}
