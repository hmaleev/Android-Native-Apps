package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

        final Context ctx = this;
        boolean isNetworkConnected = Utils.IsNetworkConnected(ctx);

        if (!isNetworkConnected){

            try {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

                alertDialog.setTitle(getString(R.string.Error_Label));
                alertDialog.setMessage(getString(R.string.Error_NoInternetConnection));
//alertDialog.setIcon(R.drawable.alerticon);
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();

                    }
                });

                alertDialog.show();
            }
            catch(Exception e)
            {
                //Log.d(Constants.TAG, "Show Dialog: "+e.getMessage());
            }

        }

        ImageView arrivalsImageView = (ImageView)findViewById(R.id.arrivalsImageView);
        arrivalsImageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), ArrivalsActivity.class);

                startActivity(nextScreen);

            }
        });

        TextView arrivalsTextView = (TextView)findViewById(R.id.arrivalsTextView);
        arrivalsTextView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), ArrivalsActivity.class);

                startActivity(nextScreen);

            }
        });

        ImageView departuresImageView = (ImageView)findViewById(R.id.departuresImageView);
        departuresImageView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), DeparturesActivity.class);

                startActivity(nextScreen);

            }
        });

        TextView departuresTextView = (TextView)findViewById(R.id.departuresTextView);
        departuresTextView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                //Starting a new Intent
                Intent nextScreen = new Intent(getApplicationContext(), DeparturesActivity.class);

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
