package com.hmaleev.sofiaairport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.hmaleev.sofiaairport.R;
import com.hmaleev.sofiaairport.models.Flight;

import java.util.ArrayList;

public class ArrivalsAdapter extends ArrayAdapter<Flight> {
    private final Context context;
    private final ArrayList<Flight> values;

    public ArrivalsAdapter(Context context,  ArrayList<Flight> values) {
        super(context, R.layout.arrivals_list_row_odd, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.arrivals_list_row_even, null);
        }



        Flight flight = values.get(position);
        TextView expectedTime = (TextView) v.findViewById(R.id.Time);
        expectedTime.setText(flight.getExpectedTime());
        TextView from = (TextView) v.findViewById(R.id.From);
        from.setText(flight.getArrivesFrom());
        TextView status = (TextView) v.findViewById(R.id.Status);
        status.setText(flight.getStatus());
        TextView terminal = (TextView) v.findViewById(R.id.Terminal);
        terminal.setText(flight.getTerminal());

        return v;
    }
}