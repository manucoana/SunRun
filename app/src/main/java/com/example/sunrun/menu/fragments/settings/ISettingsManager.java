package com.example.sunrun.menu.fragments.settings;

public interface ISettingsManager {
    void setOnGoalUpdatedListener(OnGoalUpdatedListener listener);

    void updateGoal();
}
