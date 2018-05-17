package com.example.admin.myapplication.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.callback.OnUserSelectListner;
import com.example.admin.myapplication.database.User;
import com.example.admin.myapplication.viewholder.UserViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    private List<User> mUserList;
    private OnUserSelectListner mCallBack;

    public UserAdapter(List<User> userList, OnUserSelectListner callBack) {
        this.mUserList = userList;
        mCallBack = callBack;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        final User user = mUserList.get(position);
        holder.mUserId.setText(user.getId());
        holder.mUserFirstName.setText(user.getFirst_name());
        holder.mUserLastName.setText(user.getLast_name());

        holder.mItemLayour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onUserSelect(user);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }
}
