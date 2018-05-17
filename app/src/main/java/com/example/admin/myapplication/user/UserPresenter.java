package com.example.admin.myapplication.user;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.admin.myapplication.database.User;
import com.example.admin.myapplication.database.UserDao;
import com.example.admin.myapplication.database.UserDataBase;
import com.example.admin.myapplication.managers.PreferenceManager;
import com.example.admin.myapplication.model.UserInfo;
import com.example.admin.myapplication.ui.MainActivity;

public class UserPresenter implements UserContract.Presenter {
    public static final String PREFERENCE_NAME = "myapppreference";
    private static SharedPreferences sSharedPreferences;
    private static final String PREF_KEY_ISDATAFETCH = "isDataFetched";

    private UserContract.View mView;
    private UserInteractor mInteractor;
    private Context mContext;
    private UserDao mUserDao;
    private SharedPreferences mPreference;


    public UserPresenter(Context context, UserContract.View mView) {
        this.mContext = context;
        this.mView = mView;
        this.mInteractor = new UserInteractor(context, this);
        this.mUserDao = UserDataBase.getInstance(mContext).getUserDao();
        sSharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        if(!isDataFetched()) {
            mInteractor.connect();
            setDataFetched(true);
        } else {
            getAllUserOffLine();
        }
    }

    @Override
    public void addUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void onSuccess(String response) {

        mInteractor.dataParsing(response);
    }

    @Override
    public void onFailde(String errorMessage, int errorCode) {

    }

    @Override
    public void onDataParseComplete(UserInfo userInfo) {
        for(User user : userInfo.getData()) {
            mUserDao.insert(user);
        }
        mView.showUserList(mUserDao.getAllUsers());
    }

    @Override
    public void onMenuAddUserClick() {
        mView.showAddUserDialog();
    }

    @Override
    public void onUserSelect(User user) {
        mView.showEditUserDialog(user);
    }

    public void setDataFetched(boolean isDataFetched) {
        SharedPreferences.Editor editor = sSharedPreferences.edit();
        editor.putBoolean(PREF_KEY_ISDATAFETCH, isDataFetched);
        editor.commit();
    }

    public boolean isDataFetched() {
        boolean isFetched = sSharedPreferences.getBoolean(PREF_KEY_ISDATAFETCH, false);
        return isFetched;
    }

    private void getAllUserOffLine() {
        mView.showUserList(mUserDao.getAllUsers());
    }

}
