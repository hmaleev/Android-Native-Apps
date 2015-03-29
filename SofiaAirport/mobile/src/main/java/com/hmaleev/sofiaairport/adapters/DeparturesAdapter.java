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

/**
 * Created by www on 14.3.2015 г..
 */
public class DeparturesAdapter extends ArrayAdapter<Flight> {
    private final Context context;
    private final ArrayList<Flight> values;

    public DeparturesAdapter(Context context,  ArrayList<Flight> values) {
        super(context, R.layout.arrivals_list_row_odd, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listRowView = convertView;
        if (listRowView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            listRowView = inflater.inflate(R.layout.arrivals_list_row_even, parent,false);
        }
        else {
            listRowView = convertView;
        }

        Flight flight = values.get(position);

        TextView expectedTime = (TextView) listRowView.findViewById(R.id.tvTime);
        expectedTime.setText(flight.getScheduledTime());
        TextView from = (TextView) listRowView.findViewById(R.id.tvFrom);
        from.setText(flight.getDepartsFor());
        TextView status = (TextView) listRowView.findViewById(R.id.tvStatus);
        status.setText(flight.getStatus());
        TextView terminal = (TextView) listRowView.findViewById(R.id.tvTerminal);
        terminal.setText(flight.getTerminal());

        RelativeLayout layout_item = (RelativeLayout) listRowView.findViewById(R.id.row);

        if(position % 2 == 0){
            layout_item.setBackgroundColor(context.getResources().getColor(R.color.airportBlueLight));
        }
        else {
            layout_item.setBackgroundColor(context.getResources().getColor(R.color.airportBlueDark));
        }

        return listRowView;
    }
}
