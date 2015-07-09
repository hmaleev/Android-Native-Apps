package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import static android.app.PendingIntent.getActivity;


public class MainActivity extends Activity {

    private static Context activityCtx;

    private boolean CheckIsGoogleMapsAvailable() {
        PackageManager pm = getPackageManager();
        try {
            pm. getPackageGids ("com.google.android.apps.maps");
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(activityCtx, getString(R.string.msgInstallGoogleMaps), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context ctx = this;
        activityCtx = ctx;
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
        if (id == R.id.action_navigateToAirport) {
            //    return true;

            if (!CheckIsGoogleMapsAvailable()){
                return  false;
            }
            Dialog dialog = createDialog();
            dialog.show();


        }

        return super.onOptionsItemSelected(item);
    }


    private Dialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(getString(R.string.lblNavigetToTerminal))
                .setItems(new String[] {getString(R.string.lblTerminal1),getString(R.string.lblTerminal2)}, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        switch (which){
                            case 0: {
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=Sofia+Airport,+Bulgaria");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                startActivity(mapIntent);
                                break;
                            }
                            case 1:{
                                Uri gmmIntentUri = Uri.parse("google.navigation:q=Sofia+Airport+Terminal+2,+Bulgaria");
                                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                                startActivity(mapIntent);
                            }
                        }
                    }
                });
        return builder.create();
    }
}