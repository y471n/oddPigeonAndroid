package com.example.mac.oddpigeon;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.parse.ParseUser;


public class InterestedInActivity extends ActionBarActivity  implements AdapterView.OnItemSelectedListener {

    private Spinner interestedInSpinner;
    private char interestedInSelected = 'm';
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_in);

        createInterestedInSpinner();



        done = (Button) findViewById(R.id.interested_in_done);
        done.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveUserInterestedIn(interestedInSelected);
                SharedPreferences settings = getSharedPreferences(SplashActivity.PREFS_NAME, 0);
                settings.edit().putBoolean("interestedInActivityDone", true).apply();
                Intent intent = new Intent(InterestedInActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void saveUserInterestedIn(char interestedInSelected) {
        ParseUser currentUser = ParseUser.getCurrentUser();
        currentUser.put("interestedIn", (String) Character.toString(interestedInSelected));
        currentUser.saveInBackground();
    }

    private void createInterestedInSpinner() {
        interestedInSpinner = (Spinner) findViewById(R.id.interested_in_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.interested_in_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        interestedInSpinner.setAdapter(adapter);
        interestedInSpinner.setOnItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.interested_in, menu);
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        parent.getItemAtPosition(position).toString().equalsIgnoreCase("male");
//        if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("male")){
          if(position == 0) {
            interestedInSelected = 'm';
        } else {
            interestedInSelected = 'f';
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}