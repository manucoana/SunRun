package com.example.sunrun.database.create;

import com.example.sunrun.database.goals.GoalEntry;
import com.example.sunrun.database.runs.RunEntry;

import java.util.List;

public interface IDatabaseRuns {
    List<RunEntry> getAllRuns();

    List<GoalEntry> getAllGoals();

    void insertRun(String date, float distance, float time);

    void insertGoal(int monthNumber, String monthName, float goal);
}
