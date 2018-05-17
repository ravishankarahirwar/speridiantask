package com.example.admin.myapplication.user;

import com.example.admin.myapplication.database.User;
import com.example.admin.myapplication.factory.RequestListner;

import java.util.List;

public interface UserContract {
    interface View {
        void showUserList(List<User> users);

        void showAddUserDialog();
        void showEditUserDialog(User user);

    }

    interface Presenter extends RequestListner {
        void addUser();
        void updateUser();

        void onMenuAddUserClick();
        void onUserSelect(User user);
    }

    interface Interactor {
        void connect();
        void dataParsing(String response);
    }


}
