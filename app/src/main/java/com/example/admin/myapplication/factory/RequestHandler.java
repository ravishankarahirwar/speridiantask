package com.example.admin.myapplication.factory;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.admin.myapplication.model.UserInfo;
import com.google.gson.Gson;

public class RequestHandler {
//    private Context mContext;
//    RequestListner mRequestListner;
//    public RequestHandler(Context context, RequestListner requestListner){
//        mContext = context;
//        mRequestListner = requestListner;
//    }
//    public void connect() {
//        RequestQueue queue = Volley.newRequestQueue(mContext);
//        String url ="https://reqres.in/api/users?page=1";
//
//// Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.v("Response", response.toString());
//                        Gson gson = new Gson();
//                        UserInfo userInfo = gson.fromJson(response, UserInfo.class);
//                        mRequestListner.onSuccess(userInfo);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.v("Response", "OnError");
//            }
//        });
//
//// Add the request to the RequestQueue.
//        queue.add(stringRequest);
//
//    }
}
