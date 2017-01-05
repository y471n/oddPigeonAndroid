package com.example.mac.oddpigeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.Parse;
import com.parse.ParseFacebookUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class SplashActivity extends ActionBarActivity {

    public static final String PREFS_NAME = "OddpigeonFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ParseObject.registerSubclass(Circle.class);

        Parse.initialize(this, "5KgtuLxLUC4xOohYdBPb5iF7Gh9V8Rv9ip11OZ3s", "NlGmziazqRD38jxmlsp4gkyypzqad2f0VzBG3rgd");
        ParseFacebookUtils.initialize("1515901401975410");


        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        final boolean seenTutorial = settings.getBoolean("seenTutorial", false);
        final boolean seenInterestedIn = settings.getBoolean("interestedInActivityDone", false);

        Thread logoTimer = new Thread() {
            public void run(){
                try{
                    int logoTimer = 0;
                    while(logoTimer < 5000){
                        sleep(100);
                        logoTimer = logoTimer +100;
                    };
                    Intent intent;
                    if (!seenTutorial) {
                        intent = new Intent(SplashActivity.this, TutorialActivity.class);
                    } else {
                        intent = new Intent(SplashActivity.this, LoginActivity.class);
                    }
                    // Check if there is a currently logged in user
                    // and they are linked to a Facebook account.
                    ParseUser currentUser = ParseUser.getCurrentUser();
                    if ((currentUser != null) && ParseFacebookUtils.isLinked(currentUser)) {
                        // Go to the user info activity
                        if (!seenInterestedIn) {
                            intent = new Intent(SplashActivity.this, InterestedInActivity.class);
                        } else {
                            intent = new Intent(SplashActivity.this, MainActivity.class);
                        }
                    }


                    startActivity(intent);
                }

                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                finally{
                    finish();
                }
            }
        };

        logoTimer.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.splash, menu);
        return true;
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
