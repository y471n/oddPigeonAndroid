package com.example.mac.oddpigeon;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends ActionBarActivity {

    private Button loginButton;
    private Dialog progressDialog;
    public static final String TAG = "FbLogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClicked();
            }
        });

//        // Testing Parse Connection
//        ParseUser user = new ParseUser();
//        user.setUsername("New Namess");
//        user.setPassword("New Pass");
//        user.setEmail("neweremail@example.com");
//
//        // other fields can be set just like with ParseObject
//        user.put("phone", "650-555-1111");
//
//        user.signUpInBackground(new SignUpCallback() {
//            @Override
//            public void done(com.parse.ParseException e) {
//                if (e == null) {
//                    // Hooray! Let them use the app now.
//                    Toast.makeText(getApplicationContext(), "Hurray.. Success", Toast.LENGTH_LONG).show();
//                } else {
//                    // Sign up didn't succeed. Look at the ParseException
//                    // to figure out what went wrong
//                    Toast.makeText(getApplicationContext(), "F***.. Failure", Toast.LENGTH_LONG).show();
//                }
//            }
//        });

    }

    private void onLoginButtonClicked() {
        LoginActivity.this.progressDialog = ProgressDialog.show(LoginActivity.this, "", "Logging in...", true);
        List<String> permissions = Arrays.asList("public_profile", "user_friends");
        ParseFacebookUtils.logIn(permissions, this, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException err) {
                LoginActivity.this.progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, "Logged in returns", Toast.LENGTH_SHORT).show();
                if (user == null) {
                    Log.d(TAG, "Uh oh. The user cancelled the Facebook login.");
                } else if (user.isNew()) {
                    Log.d(TAG, "User signed up and logged in through Facebook!");
                    showUserDetailsActivity();
                } else {
                    Log.d(TAG, "User logged in through Facebook!");
                    showUserDetailsActivity();
                }
            }
        });
    }

    private void showUserDetailsActivity()
    {
            Intent intent = new Intent(LoginActivity.this, InterestedInActivity.class);
            startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
