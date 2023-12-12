package com.example.sunrun.database.goals;

import androidx.annotation.NonNull;

public class GoalEntry {
    private final String id;

    private final int monthNumber;

    private final String monthName;
    private final float goal;

    public GoalEntry(String id, int monthNumber, String monthName, float goal) {
        this.id = id;
        this.monthNumber = monthNumber;
        this.monthName = monthName;
        this.goal = goal;
    }

    public String getId() {
        return id;
    }


    public String getMonthName() {
        return monthName;
    }

    public float getGoal() {
        return goal;
    }

    @NonNull
    @Override
    public String toString() {
        return "GoalEntry{" +
                "id='" + id + '\'' +
                ", monthNumber=" + monthNumber +
                ", monthName='" + monthName + '\'' +
                ", goal=" + goal +
                '}';
    }
}
