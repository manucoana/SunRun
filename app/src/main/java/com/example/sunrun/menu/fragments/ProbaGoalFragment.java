package com.example.sunrun.menu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunrun.R;
import com.example.sunrun.database.create.DatabaseRuns;

import com.example.sunrun.database.goals.GoalAdapter;
import com.example.sunrun.database.goals.GoalEntry;

import java.util.List;

public class ProbaGoalFragment extends Fragment {

    public ProbaGoalFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_months, container, false); // Use goals_layout.xml

        ListView listView = view.findViewById(R.id.listViewGoals);

        DatabaseRuns databaseRuns = new DatabaseRuns(getContext());

        List<GoalEntry> goalEntries = databaseRuns.getAllGoals();

        GoalAdapter goalAdapter = new GoalAdapter(getContext(), R.layout.single_goal_details_layout, goalEntries);

        listView.setAdapter(goalAdapter);

        goalAdapter.notifyDataSetChanged();

        return view;
    }
}
