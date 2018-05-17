package com.example.admin.myapplication.factory;

import com.example.admin.myapplication.model.UserInfo;

import java.util.List;

public interface RequestListner {
    void onSuccess(String response);
    void onFailde(String errorMessage, int errorCode);
    void onDataParseComplete(UserInfo userInfo);

}
