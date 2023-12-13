package com.example.sunrun.menu.fragments.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sunrun.R;

public class SettingsFragment extends Fragment implements OnGoalUpdatedListener {
    private SettingsManager settingsManager;

    public SettingsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_settings_layout, container, false);

        Spinner spinnerMonth = view.findViewById(R.id.spinnerMonth);
        EditText editTextGoal = view.findViewById(R.id.editTextGoal);
        Button buttonSetGoal = view.findViewById(R.id.buttonSetGoal);

        String[] months = getResources().getStringArray(R.array.months_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, months);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMonth.setAdapter(adapter);

        settingsManager = new SettingsManager(getContext(), spinnerMonth, editTextGoal);

        buttonSetGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsManager.updateGoal();
            }
        });

        settingsManager.setOnGoalUpdatedListener(this);

        return view;
    }

    @Override
    public void onGoalUpdated(String month, float goal) {
    }
}
