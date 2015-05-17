package com.hmaleev.sofiaairport;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.hmaleev.sofiaairport.adapters.DeparturesAdapter;
import com.hmaleev.sofiaairport.models.Flight;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public final class DeparturesActivity extends Activity {

    private static boolean headerExists = false;
    private static String url ="";
    private static Context activityContext = null;
    private Handler mHandler;
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_departures);


        super.onCreate(savedInstanceState);
        progress =  progress.show(this,getString(R.string.Label_Loading),getString(R.string.Msg_Wait));
        headerExists = false;

        updateUI();
    }

    private void updateUI() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String flightCount = sharedPref.getString(SettingsActivity.PREF_FLIGHT_COUNT, "1");
        String baseUrl = "http://sofiaairport.apphb.com/api/departures/getall?size=";
        url = baseUrl +flightCount;

        boolean isAutoSyncEnabled = sharedPref.getBoolean(SettingsActivity.PREF_AUTO_SYNC,true);


        final Context ctx = this;
        activityContext = ctx;

        if (isAutoSyncEnabled) {
            if (this.mHandler == null) {
                this.mHandler = new Handler();
                m_Runnable.run();

            }
        }
        else {
            if (this.mHandler != null) {
                mHandler.removeCallbacksAndMessages(m_Runnable);
            }

        }

        final ArrayList<Flight> listData = new ArrayList<>();

        final RequestQueue rq = Volley.newRequestQueue(this);
        final JsonArrayRequest jReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        updateFlightData(response, listData);

                        final ListView departuresListView = (ListView) findViewById(R.id.lvDepartures);
                        if (!headerExists) {
                            addListViewHeader(departuresListView);
                            TextView label = (TextView)findViewById(R.id.tvFrom);
                            label.setText(R.string.departsForLabel);
                        }

                        final DeparturesAdapter adapter = new DeparturesAdapter(ctx, listData);
                        departuresListView.setAdapter(adapter);

                        navigateToNextViewClickHandler(departuresListView);
                        adapter.notifyDataSetChanged();
                        progress.dismiss();

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });
        int socketTimeout = 10000;//10 seconds
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        jReq.setRetryPolicy(policy);
        rq.add(jReq);

        final SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swcontDepartures);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.


                final JsonArrayRequest jReq = new JsonArrayRequest(url,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(JSONArray response) {
                                listData.clear();
                                updateFlightData(response, listData);
                                final ListView departuresListView = (ListView) findViewById(R.id.lvDepartures);
                                if (!headerExists) {
                                    addListViewHeader(departuresListView);
                                }
                                final DeparturesAdapter adapter = new DeparturesAdapter(ctx, listData);

                                departuresListView.setAdapter(adapter);
                                navigateToNextViewClickHandler(departuresListView);

                                adapter.notifyDataSetChanged();
                                swipeContainer.setRefreshing(false);

                            }
                        }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error

                    }
                });
                rq.add(jReq);
            }

        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(R.color.airportBlueLight,
                R.color.airportBlueDark);
    }

    @Override
    public void onRestart(){
        super.onRestart();
        updateUI();
    }

    private final Runnable m_Runnable = new Runnable()
    {

        public void run()

        {
          //   Toast.makeText(DeparturesActivity.this, "updated", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activityContext);
            String autoSyncInterval = sharedPref.getString(SettingsActivity.PREF_AUTO_SYNC_INTERVAL, "60000");
            int interval = Integer.parseInt(autoSyncInterval);
            final ArrayList<Flight> listData = new ArrayList<>();
            final RequestQueue rq = Volley.newRequestQueue(activityContext);

            final JsonArrayRequest request = getJsonArrayRequest(activityContext,listData);
            int socketTimeout = 10000;//10 seconds
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            request.setRetryPolicy(policy);
            rq.add(request);

            DeparturesActivity.this.mHandler.postDelayed(m_Runnable,interval);
        }

    };

    private JsonArrayRequest getJsonArrayRequest(final Context ctx, final ArrayList<Flight> listData) {
        return new JsonArrayRequest(url,
                    new Response.Listener<JSONArray>() {

                        @Override
                        public void onResponse(JSONArray response) {

                            listData.clear();
                            updateFlightData(response, listData);

                            final ListView departuresListView = (ListView) findViewById(R.id.lvDepartures);
                            if (!headerExists) {
                                addListViewHeader(departuresListView);
                            }
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
    }


    private void addListViewHeader(ListView departuresListView) {
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup)inflater.inflate(R.layout.arrivals_list_row_header, departuresListView, false);
        departuresListView.addHeaderView(header, null, false);
        headerExists = true;
    }

    private void updateFlightData(JSONArray response, ArrayList<Flight> listData) {
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
                Log.e("ERROR", e.getMessage());
            }
        }
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
            Intent nextScreen = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(nextScreen);
        }

        return super.onOptionsItemSelected(item);
    }

    private void navigateToNextViewClickHandler(final ListView departuresListView) {
        departuresListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> currentAdapter, View currentView, int position, long arg3) {

                Flight currentFlight = (Flight) departuresListView.getItemAtPosition(position);
                Intent nextScreen = new Intent(getApplicationContext(), FlightDetailsActivity.class);
                nextScreen.putExtra("flightDetails", currentFlight);
                nextScreen.putExtra("parentActivity","departures");

                startActivity(nextScreen);
            }
        });
    }
}
