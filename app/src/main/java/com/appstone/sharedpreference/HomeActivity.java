package com.appstone.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView mTvUserName;
    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTvUserName = findViewById(R.id.tv_user_name);
        prefManager = getSharedPreferences("APP_NAME", MODE_PRIVATE);
        editor = prefManager.edit();


        String userName = prefManager.getString(LoginActivity.KEY_EMAIL_ADDRESS, "No Value available");

        mTvUserName.setText(userName);

    }

    public void onLogoutClicked(View view) {
        editor.putBoolean(LoginActivity.KEY_IS_USER_LOGGED_IN, false);
        editor.putString(LoginActivity.KEY_PASSWORD, "");
        //Todo code commented so that after logout the username and email address is prefilled.
//        editor.putString(LoginActivity.KEY_USERNAME, "");
//        editor.putString(LoginActivity.KEY_EMAIL_ADDRESS, "");
        editor.apply();

        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        finish();
    }
}
