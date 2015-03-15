package com.hmaleev.sofiaairport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
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
          /*  if (position == 0) {
                v = inflater.inflate(R.layout.arrivals_list_row_header, null);

                TextView expectedTimeLabe = (TextView) v.findViewById(R.id.tvTime);
                expectedTimeLabe.setText(R.string.expectedTimeHeaderLabel);
                TextView fromHeader = (TextView) v.findViewById(R.id.tvFrom);
                fromHeader.setText(R.string.arrivesFromHeaderLabel);
                TextView statusHeader = (TextView) v.findViewById(R.id.tvStatus);
                statusHeader.setText(R.string.statusHeaderLabel);
                TextView terminal = (TextView) v.findViewById(R.id.tvTerminal);
                terminal.setText(R.string.terminaHeaderlLabel);

                return v;
            }*/
            if(position % 2 == 0){
                v = inflater.inflate(R.layout.arrivals_list_row_even, null);
            }
            else {
                v = inflater.inflate(R.layout.arrivals_list_row_odd, null);
            }
        }

        Flight flight = values.get(position);
        TextView expectedTime = (TextView) v.findViewById(R.id.tvTime);
        expectedTime.setText(flight.getExpectedTime());
        TextView from = (TextView) v.findViewById(R.id.tvFrom);
        from.setText(flight.getArrivesFrom());
        TextView status = (TextView) v.findViewById(R.id.tvStatus);
        status.setText(flight.getStatus());
        TextView terminal = (TextView) v.findViewById(R.id.tvTerminal);
        terminal.setText(flight.getTerminal());

        return v;
    }
}