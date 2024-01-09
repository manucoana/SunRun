package com.example.sunrun.menu.fragments.progress;

import android.widget.ProgressBar;

import com.example.sunrun.database.create.DatabaseRuns;

public class ProgressManager {

    private ProgressBar progressBar;
    private DatabaseRuns databaseRuns;
    private double monthlyGoal;
    private int lastUpdatedMonth;

    public ProgressManager(ProgressBar progressBar, DatabaseRuns databaseRuns, double monthlyGoal) {
        this.progressBar = progressBar;
        this.databaseRuns = databaseRuns;
        this.monthlyGoal = monthlyGoal;
    }


}