package com.hmaleev.sofiaairport.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
            listRowView = inflater.inflate(R.layout.arrivals_list_row_even, null);
        }



        Flight c = values.get(position);
        TextView expectedTime = (TextView) listRowView.findViewById(R.id.Time);
        expectedTime.setText(c.ExpectedTime);
        TextView from = (TextView) listRowView.findViewById(R.id.From);
        from.setText(c.ArrivesFrom);
        TextView status = (TextView) listRowView.findViewById(R.id.Status);
        status.setText(c.Status);
        TextView terminal = (TextView) listRowView.findViewById(R.id.Terminal);
        terminal.setText(c.Terminal);

        return listRowView;
    }
}
