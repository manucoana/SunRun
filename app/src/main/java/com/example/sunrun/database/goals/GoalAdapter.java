package com.example.sunrun.database.goals;

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

public class GoalAdapter extends ArrayAdapter<GoalEntry> {
    private final Context context;
    private final int resource;
    private final List<GoalEntry> goalEntries;

    public GoalAdapter(@NonNull Context context, int resource, @NonNull List<GoalEntry> goalEntries) {
        super(context, resource, goalEntries);
        this.context = context;
        this.resource = resource;
        this.goalEntries = goalEntries;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        GoalEntry goalEntry = goalEntries.get(position);

        TextView textViewGoalDetails = convertView.findViewById(R.id.textViewGoalDetails);
        TextView textViewGoalMonth = convertView.findViewById(R.id.textViewGoalMonth);
        TextView textViewGoalAmount = convertView.findViewById(R.id.textViewGoalAmount);

        if (textViewGoalDetails != null) {
            textViewGoalDetails.setText("Goal " + (position + 1));
        }

        if (textViewGoalMonth != null) {
            textViewGoalMonth.setText("Month: " + goalEntry.getMonthName());
        }

        if (textViewGoalAmount != null) {
            textViewGoalAmount.setText("Goal Amount: " + goalEntry.getGoal());
        }

        return convertView;
    }
}
