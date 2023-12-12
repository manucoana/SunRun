package com.example.sunrun.menu.manager;

import android.os.Build;
import android.widget.ProgressBar;


import androidx.annotation.RequiresApi;

import com.example.sunrun.database.create.DatabaseRuns;
import com.example.sunrun.database.runs.RunEntry;

import java.util.Calendar;
import java.util.List;

public class ProgressManager {

    private ProgressBar progressBar;
    private DatabaseRuns databaseRuns;
    private double monthlyGoal;
    private int lastUpdatedMonth;

    public ProgressManager(ProgressBar progressBar, DatabaseRuns databaseRuns, double monthlyGoal, int lastUpdatedMonth) {
        this.progressBar = progressBar;
        this.databaseRuns = databaseRuns;
        this.monthlyGoal = monthlyGoal;
        this.lastUpdatedMonth = lastUpdatedMonth;
    }


}