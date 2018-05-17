package com.example.admin.myapplication.user;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.myapplication.factory.RequestListner;
import com.example.admin.myapplication.model.UserInfo;
import com.google.gson.Gson;

public class UserInteractor implements UserContract.Interactor {
    private Context mContext;
    private RequestListner mCallback;

    public UserInteractor(Context context, RequestListner callBack) {
        mContext = context;
        mCallback = callBack;
    }

    @Override
    public void connect() {
        RequestQueue queue = Volley.newRequestQueue(mContext);
        String url ="https://reqres.in/api/users?page=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.v("Response", response.toString());
                        mCallback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Response", "OnError");
                mCallback.onFailde("Not able to connect", 400);
            }
        });
        queue.add(stringRequest);
    }

    @Override
    public void dataParsing(String response) {
        Gson gson = new Gson();
        UserInfo userInfo = gson.fromJson(response, UserInfo.class);
        mCallback.onDataParseComplete(userInfo);
    }
}
