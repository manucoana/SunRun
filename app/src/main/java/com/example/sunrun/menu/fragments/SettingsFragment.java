package com.example.sunrun.menu.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunrun.R;
import com.example.sunrun.database.create.DatabaseRuns;
import com.example.sunrun.menu.interfaces.OnGoalUpdatedListener;

public class SettingsFragment extends Fragment {
    private Spinner spinnerMonth;
    private EditText editTextGoal;
    private OnGoalUpdatedListener onGoalUpdatedListener;
    private DatabaseRuns databaseRuns;

    public SettingsFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_settings_layout, container, false);

        spinnerMonth = view.findViewById(R.id.spinnerMonth);
        editTextGoal = view.findViewById(R.id.editTextGoal);
        Button buttonSetGoal = view.findViewById(R.id.buttonSetGoal);

        databaseRuns = new DatabaseRuns(getActivity());

        String[] months = getResources().getStringArray(R.array.months_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMonth.setAdapter(adapter);

        buttonSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateGoal();
            }
        });

        return view;
    }

    private void updateGoal() {
        String month = spinnerMonth.getSelectedItem().toString();
        float goal = Float.parseFloat(editTextGoal.getText().toString());

        if (onGoalUpdatedListener != null) {
            onGoalUpdatedListener.onGoalUpdated(month, goal);
        }

        updateGoalInDatabase(month, goal);
    }

    private void updateGoalInDatabase(String month, float goal) {
        int monthNumber = getMonthNumber(month);
        if (monthNumber != -1) {
            databaseRuns.insertGoal(monthNumber, month, goal);
            showToast("Goal for " + month + " updated successfully");
        }
    }

    private int getMonthNumber(String month) {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        for (int i = 0; i < monthNames.length; i++) {
            if (monthNames[i].equalsIgnoreCase(month)) {
                return i + 1;
            }
        }
        return -1;
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
