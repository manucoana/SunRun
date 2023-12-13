package com.example.sunrun.menu.fragments.settings;

import android.content.Context;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sunrun.database.create.DatabaseRuns;

public class SettingsManager implements ISettingsManager {
    private final Spinner spinnerMonth;
    private final EditText editTextGoal;
    private OnGoalUpdatedListener onGoalUpdatedListener;
    private final DatabaseRuns databaseRuns;
    private final Context context;

    public SettingsManager(Context context, Spinner spinnerMonth, EditText editTextGoal) {
        this.context = context;
        this.spinnerMonth = spinnerMonth;
        this.editTextGoal = editTextGoal;
        this.databaseRuns = new DatabaseRuns(context);
    }

    @Override
    public void setOnGoalUpdatedListener(OnGoalUpdatedListener listener) {
        this.onGoalUpdatedListener = listener;
    }

    @Override
    public void updateGoal() {
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
        Toast.makeText(context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
