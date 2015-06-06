package com.hmaleev.sofiaairport.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hmaleev.sofiaairport.ArrivalsActivity;
import com.hmaleev.sofiaairport.R;
import com.hmaleev.sofiaairport.models.Flight;

import java.util.ArrayList;

public class ArrivalsAdapter extends ArrayAdapter<Flight> {
    private final Context context;
    private final ArrayList<Flight> values;

    public ArrivalsAdapter(Context context,  ArrayList<Flight> values) {
        super(context, R.layout.flight_list_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.flight_list_row, parent,false);
        }
        else {
            v = convertView;
        }
        Flight flight = values.get(position);
        TextView expectedTime = (TextView) v.findViewById(R.id.tvTime);
        expectedTime.setText(flight.getScheduledTime());
        TextView from = (TextView) v.findViewById(R.id.tvFrom);
        from.setText(flight.getArrivesFrom());
        TextView status = (TextView) v.findViewById(R.id.tvStatus);
        status.setText(flight.getStatus());
        TextView terminal = (TextView) v.findViewById(R.id.tvTerminal);
        terminal.setText(flight.getTerminal());

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        float dp = width / (metrics.densityDpi / 160f);

        if (dp>480) {
          /*  LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.flight_list_row, parent,false);*/
            TextView expected = (TextView) v.findViewById(R.id.tvExpected);
            expected.setText(flight.getExpectedTime());
            TextView flightNo = (TextView) v.findViewById(R.id.tvFlightNo);
            flightNo.setText(flight.getFlightNo());
        }

        RelativeLayout layout_item = (RelativeLayout) v.findViewById(R.id.row);

        if(position % 2 == 0){
            layout_item.setBackgroundColor(context.getResources().getColor(R.color.airportBlueLight));
            }
            else {
            layout_item.setBackgroundColor(context.getResources().getColor(R.color.airportBlueDark));
            }
        return v;
    }
}