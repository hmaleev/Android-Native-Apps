package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView arrivalsImageView = (ImageView)findViewById(R.id.arrivalsImageView);
        arrivalsImageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), Arrivals.class);

                startActivity(nextScreen);

            }
        });

        TextView arrivalsTextView = (TextView)findViewById(R.id.arrivalsTextView);
        arrivalsTextView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), Arrivals.class);

                startActivity(nextScreen);

            }
        });

        ImageView departuresImageView = (ImageView)findViewById(R.id.departuresImageView);
        departuresImageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), Departures.class);

                startActivity(nextScreen);

            }
        });

        TextView departuresTextView = (TextView)findViewById(R.id.departuresTextView);
        departuresTextView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), Departures.class);

                startActivity(nextScreen);

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
        //    return true;
           Intent nextScreen = new Intent(getApplicationContext(), SettingsActivity.class);
           startActivity(nextScreen);
        }

        return super.onOptionsItemSelected(item);
    }
}
