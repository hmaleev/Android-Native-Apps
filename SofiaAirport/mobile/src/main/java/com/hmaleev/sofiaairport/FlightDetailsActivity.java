package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;
import com.hmaleev.sofiaairport.models.Flight;

import org.w3c.dom.Text;


public class FlightDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        Flight flight = (Flight) getIntent().getSerializableExtra("flightDetails");
        String parentActivity =  getIntent().getStringExtra("parentActivity");

        TextView scheduledTime = (TextView)findViewById(R.id.scheduledTimeValue);
        scheduledTime.setText(flight.getScheduledTime());

        TextView flightNo = (TextView)findViewById(R.id.flightNoValue);
        flightNo.setText(flight.getFlightNo());

        if (parentActivity.equalsIgnoreCase("departures")){
            TextView label = (TextView)findViewById(R.id.arrivesFromLabel);
            label.setText(R.string.departsForLabel);
            TextView arrivesFrom = (TextView)findViewById(R.id.arrivesFromValue);
            arrivesFrom.setText(flight.getDepartsFor());
        }
        else {
            TextView label = (TextView)findViewById(R.id.arrivesFromLabel);
            label.setText(R.string.arrivesFromLabel);
            TextView arrivesFrom = (TextView) findViewById(R.id.arrivesFromValue);
            arrivesFrom.setText(flight.getArrivesFrom());
        }
        TextView expectedTime = (TextView)findViewById(R.id.expectedTimeValue);
        expectedTime.setText(flight.getExpectedTime());

        TextView status = (TextView)findViewById(R.id.statusValue);
        status.setText(flight.getStatus());

        TextView terminal = (TextView)findViewById(R.id.terminalValue);
        terminal.setText(flight.getTerminal());

        TextView groundOperator = (TextView)findViewById(R.id.groundOperatorValue);
        groundOperator.setText(flight.getGroundOperator());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flight_details, menu);
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
            Intent nextScreen = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(nextScreen);
        }

        return super.onOptionsItemSelected(item);
    }
}
