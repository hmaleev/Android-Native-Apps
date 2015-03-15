package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.hmaleev.sofiaairport.models.Flight;


public class FlightDetailsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        Flight flight = (Flight) getIntent().getSerializableExtra("flightDetails");

        TextView scheduledTime = (TextView)findViewById(R.id.scheduledTimeValue);
        scheduledTime.setText(flight.getScheduledTime());

        TextView flightNo = (TextView)findViewById(R.id.flightNoValue);
        flightNo.setText(flight.getFlightNo());

        TextView arrivesFrom = (TextView)findViewById(R.id.arrivesFromValue);
        arrivesFrom.setText(flight.getArrivesFrom());

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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
