package com.example.admin.myapplication.model;

import com.example.admin.myapplication.database.User;

import java.util.ArrayList;
import java.util.List;

public class UserInfo {

    private List<User> data = new ArrayList<>();

    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

}
