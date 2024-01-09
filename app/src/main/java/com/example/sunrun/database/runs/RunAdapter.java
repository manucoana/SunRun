package com.example.sunrun.database.runs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.sunrun.R;

import java.util.List;

public class RunAdapter extends ArrayAdapter<RunEntry> {
    private final Context context;
    private final int resource;
    private final List<RunEntry> runEntries;

    public RunAdapter(@NonNull Context context, int resource, @NonNull List<RunEntry> runEntries) {
        super(context, resource, runEntries);
        this.context = context;
        this.resource = resource;
        this.runEntries = runEntries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        RunEntry runEntry = runEntries.get(position);

        TextView textViewRunDetails = convertView.findViewById(R.id.textViewRunDetails);
        TextView textViewRunDistance = convertView.findViewById(R.id.textViewRunDistance);
        TextView textViewRunDuration = convertView.findViewById(R.id.textViewRunTime);
        TextView textViewRunTimeMinutes = convertView.findViewById(R.id.textViewRunTimeMinutes);

        textViewRunDetails.setText("Run " + (position + 1));
        textViewRunDistance.setText("Distance: " + runEntry.getDistance() + " km");
        textViewRunDuration.setText("Date: " + runEntry.getDate());
        textViewRunTimeMinutes.setText("Minutes: " + runEntry.getTime());

        return convertView;
    }
}
