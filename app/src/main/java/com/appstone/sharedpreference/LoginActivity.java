package com.appstone.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtUsername;
    private EditText mEtEmailAddress;
    private EditText mEtPassword;
    private CheckBox mChkRememberMe;

    private SharedPreferences prefManager;
    private SharedPreferences.Editor editor;

    public static String KEY_USERNAME = "USERNAME";
    public static String KEY_EMAIL_ADDRESS = "EMAILADDRESS";
    public static String KEY_PASSWORD = "PASSWORD";
    public static String KEY_IS_USER_LOGGED_IN = "ISUSERLOGGEDIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEtUsername = findViewById(R.id.et_user_name);
        mEtEmailAddress = findViewById(R.id.et_email_address);
        mEtPassword = findViewById(R.id.et_password);

        mChkRememberMe = findViewById(R.id.chk_remember);

        prefManager = getSharedPreferences("APP_NAME", MODE_PRIVATE);
        editor = prefManager.edit();


        boolean isUserAlreadyLoggedIn = prefManager.getBoolean("ISUSERLOGGEDIN", false);
        String userName = prefManager.getString(KEY_USERNAME, "");
        String emailAddress = prefManager.getString(KEY_EMAIL_ADDRESS, "");

        mEtUsername.setText(userName);
        mEtEmailAddress.setText(emailAddress);

        if (isUserAlreadyLoggedIn) {
            moveToHomeScreen();
        }
    }


    public void onLoginClicked(View view) {
        String username = mEtUsername.getText().toString();
        String emailAddress = mEtEmailAddress.getText().toString();
        String password = mEtPassword.getText().toString();

        boolean isRememberMe = mChkRememberMe.isChecked();

        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_EMAIL_ADDRESS, emailAddress);
        editor.putString(KEY_PASSWORD, password);

        if (isRememberMe) {
            editor.putBoolean(KEY_IS_USER_LOGGED_IN, true);
        }
        editor.apply();

        moveToHomeScreen();
    }

    private void moveToHomeScreen() {
        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        finish();
    }
}
