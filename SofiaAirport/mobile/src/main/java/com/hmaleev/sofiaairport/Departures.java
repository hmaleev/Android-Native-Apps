package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.content.Context;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hmaleev.sofiaairport.adapters.ArrivalsAdapter;
import com.hmaleev.sofiaairport.adapters.DeparturesAdapter;
import com.hmaleev.sofiaairport.models.Flight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Departures extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_departures);

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://sofiaairport.apphb.com/api/departures/getall?size=1";
        final Context ctx = this;
// Request a string response from the provided URL.
        final ArrayList<Flight> listData = new ArrayList<Flight>();
        RequestQueue rq = Volley.newRequestQueue(this);
        JsonArrayRequest jReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        List<Object> result = new ArrayList<Object>();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject x = response.getJSONObject(i);
                                Flight flight = new Flight();
                                flight.setDepartsFor(x.getString("DepartsFor"));
                                flight.setExpectedTime( x.getString("ExpectedTime") );
                                flight.setFlightNo( x.getString("FlightNo") );
                                flight.setDepartsFor( x.getString("DepartsFor") );
                                flight.setGroundOperator( x.getString("GroundOperator") );
                                flight.setMoreDetails( x.getString("MoreDetails") );
                                flight.setPlaneType( x.getString("PlaneType"));
                                flight.setScheduledDate( x.getString("ScheduledDate") );
                                flight.setScheduledTime( x.getString("ScheduledTime") );
                                flight.setStatus( x.getString("Status") );
                                flight.setTerminal( x.getString("Terminal") );
                                listData.add(flight);

                            } catch (JSONException e) {
                            }

                        }
                        final ListView departuresListView = (ListView) findViewById(R.id.departuresListView);

                        final DeparturesAdapter adapter = new DeparturesAdapter(ctx, listData);
                        departuresListView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error

            }
        });

        rq.add(jReq);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_departures, menu);
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
