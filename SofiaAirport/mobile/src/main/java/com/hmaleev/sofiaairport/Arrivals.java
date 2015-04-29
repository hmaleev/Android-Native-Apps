package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hmaleev.sofiaairport.adapters.ArrivalsAdapter;
import com.hmaleev.sofiaairport.models.Flight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public final class Arrivals extends Activity {

    private SwipeRefreshLayout swipeContainer;


    private static String url ="";
    private static boolean headerExists = false;
    private Handler mHandler;
    private static Context activityContext = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrivals);
        headerExists = false;

        updateUI();

        }

    private void updateUI() {
        final Context ctx = this;
        activityContext = ctx;
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String flightCount = sharedPref.getString(SettingsActivity.PREF_FLIGHT_COUNT, "2");
        String baseUrl = "http://sofiaairport.apphb.com/api/arrivals/getall?size=";
        url = baseUrl  +flightCount;

        if (this.mHandler == null) {
            this.mHandler = new Handler();
            m_Runnable.run();
        }


        final ArrayList<Flight> listData = new ArrayList<>();
        final RequestQueue rq = Volley.newRequestQueue(this);
        final JsonArrayRequest jReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        updateFlightData(response, listData);

                        final ListView arrivalsListView = (ListView) findViewById(R.id.lvArrivals);
                        if (!headerExists) {
                            addListViewHeader(arrivalsListView);
                        }

                        final ArrivalsAdapter adapter = new ArrivalsAdapter(ctx, listData);
                        arrivalsListView.setAdapter(adapter);

                        navigateToNextViewClickHandler(arrivalsListView);
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });


        rq.add(jReq);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                final JsonArrayRequest request = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                listData.clear();
                                updateFlightData(response, listData);
                                final ListView arrivalsListView = (ListView) findViewById(R.id.lvArrivals);
                                final ArrivalsAdapter adapter = new ArrivalsAdapter(ctx, listData);

                                arrivalsListView.setAdapter(adapter);
                                navigateToNextViewClickHandler(arrivalsListView);

                                adapter.notifyDataSetChanged();
                                swipeContainer.setRefreshing(false);

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error

                    }
                });
                rq.add(request);
            }

        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.airportBlueLight,
                R.color.airportBlueDark);
    }

    private final Runnable m_Runnable = new Runnable()
    {

        public void run()

        {
           // Toast.makeText(Arrivals.this, "in runnable", Toast.LENGTH_SHORT).show();
            final ArrayList<Flight> listData = new ArrayList<>();
            final RequestQueue rq = Volley.newRequestQueue(activityContext);
            final JsonArrayRequest request = new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {
                            listData.clear();
                            updateFlightData(response, listData);
                            final ListView arrivalsListView = (ListView) findViewById(R.id.lvArrivals);
                            final ArrivalsAdapter adapter = new ArrivalsAdapter(activityContext, listData);

                            arrivalsListView.setAdapter(adapter);
                            navigateToNextViewClickHandler(arrivalsListView);

                            adapter.notifyDataSetChanged();
                           // swipeContainer.setRefreshing(false);
                            Toast.makeText(Arrivals.this, "updated", Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // Handle error

                }
            });
            rq.add(request);

            Arrivals.this.mHandler.postDelayed(m_Runnable,60000);
        }

    };

    @Override
    protected void onRestart(){
        super.onRestart();
        updateUI();

    }

    private void addListViewHeader(ListView arrivalsListView) {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.arrivals_list_row_header, arrivalsListView, false);
        arrivalsListView.addHeaderView(header, null, false);
        headerExists = true;

    }

    private void navigateToNextViewClickHandler(final ListView arrivalsListView) {
        arrivalsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> currentAdapter, View currentView, int position, long arg3) {

                Flight currentFlight = (Flight) arrivalsListView.getItemAtPosition(position);
                Intent nextScreen = new Intent(getApplicationContext(), FlightDetailsActivity.class);
                nextScreen.putExtra("flightDetails", currentFlight);

                startActivity(nextScreen);
            }
        });
    }

    private void updateFlightData(JSONArray response, ArrayList<Flight> listData) {

        for (int i = 0; i < response.length(); i++) {
            try {
                JSONObject x = response.getJSONObject(i);
                Flight flight = new Flight();
                flight.setArrivesFrom(x.getString("ArrivesFrom"));
                flight.setExpectedTime(x.getString("ExpectedTime"));
                flight.setFlightNo(x.getString("FlightNo"));
                flight.setDepartsFor(x.getString("DepartsFor"));
                flight.setGroundOperator(x.getString("GroundOperator"));
                flight.setMoreDetails(x.getString("MoreDetails"));
                flight.setPlaneType(x.getString("PlaneType"));
                flight.setScheduledDate(x.getString("ScheduledDate"));
                flight.setScheduledTime(x.getString("ScheduledTime"));
                flight.setStatus(x.getString("Status"));
                flight.setTerminal(x.getString("Terminal"));
                listData.add(flight);

            } catch (JSONException e) {
                Log.e("ERROR", e.getMessage());
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_arrivals, menu);
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