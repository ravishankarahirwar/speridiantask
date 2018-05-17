package com.example.admin.myapplication.ui;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.adapter.UserAdapter;
import com.example.admin.myapplication.callback.OnUserSelectListner;
import com.example.admin.myapplication.database.User;
import com.example.admin.myapplication.database.UserDataBase;
import com.example.admin.myapplication.factory.RequestHandler;
import com.example.admin.myapplication.factory.RequestListner;
import com.example.admin.myapplication.model.UserInfo;
import com.example.admin.myapplication.user.UserContract;
import com.example.admin.myapplication.user.UserPresenter;

import java.util.List;

public class MainActivity extends BaseActivity implements UserContract.View, OnUserSelectListner {

    UserPresenter mUserPresenter;
    private RecyclerView mUserList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    @Override
    public void init() {
        mUserList = findViewById(R.id.user_list);
        mUserList.setLayoutManager(new LinearLayoutManager(this));
        mUserPresenter = new UserPresenter(this, this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_user) {
            mUserPresenter.onMenuAddUserClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onUserSelect(User user) {
        mUserPresenter.onUserSelect(user);
    }

    @Override
    public void showUserList(List<User> users) {
        mUserList.setAdapter(new UserAdapter(users, this));
    }

    @Override
    public void showEditUserDialog(User user) {
        showEditDialog(user);
    }

    public void showAddUserDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        dialogBuilder.setView(dialogView);

        final TextView userId = (TextView) dialogView.findViewById(R.id.user_id);
        final EditText userFirstName = (EditText) dialogView.findViewById(R.id.user_fname);
        final EditText userLastName = (EditText) dialogView.findViewById(R.id.user_lname);

        int userCount = UserDataBase
                .getInstance(MainActivity.this)
                .getUserDao()
                .getUsersCount();

        userId.append(String.valueOf(++userCount));

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String firstName = userFirstName.getText().toString();
                String lastName = userLastName.getText().toString();

                int userCount = UserDataBase
                        .getInstance(MainActivity.this)
                        .getUserDao()
                        .getUsersCount();

                UserDataBase
                        .getInstance(MainActivity.this)
                        .getUserDao()
                        .insert(new User(String.valueOf(++userCount), firstName, lastName, "Fb url"));
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }


    public void showEditDialog(final User user) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_add_user, null);
        dialogBuilder.setView(dialogView);

        final TextView userId = (TextView) dialogView.findViewById(R.id.user_id);
        final EditText userFirstName = (EditText) dialogView.findViewById(R.id.user_fname);
        final EditText userLastName = (EditText) dialogView.findViewById(R.id.user_lname);

        userId.append(user.getId());
        userFirstName.setText(user.getFirst_name());
        userLastName.setText(user.getLast_name());

        dialogBuilder.setTitle("Custom dialog");
        dialogBuilder.setMessage("Enter text below");
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //do something with edt.getText().toString();
                String firstName = userFirstName.getText().toString();
                String lastName = userLastName.getText().toString();

                UserDataBase
                        .getInstance(MainActivity.this)
                        .getUserDao()
                        .update(new User(user.getId(), firstName, lastName, "Fb url"));
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
}



//1) Create a project in android studio
//
//        2) Display a list of users in a recycler view using the below dummy url:
//        https://reqres.in/api/users?page=1
//
//        3) Make use of card View to display user information in recycler view item.
//
//        4) Provide a icon at toolbar, to add a new user. On click of it, show a pop up where user can add a new user. Add this user in database(No API calls).
//
//        5) On click of individual item in recycler view, open a dialog, with 2 options, delete item, edit item.
//
//        No need to make API calls for Delete and Edit User, perform these operations on local database.
//
//        6) Write Junit tests for Add, Delete and Edit User.
//
//        7)
//
//
//
//        Database:
//        Use either sqlite or Room database.
//
//        8) Once done share your code, along with the apk file.