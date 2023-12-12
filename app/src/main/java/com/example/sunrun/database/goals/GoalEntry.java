package com.example.sunrun.database.goals;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

public class GoalEntry  {
    private String id;

    private int monthNumber;

    private String monthName;
    private float goal;

    public GoalEntry(String id, int monthNumber, String monthName, float goal) {
        this.id = id;
        this.monthNumber = monthNumber;
        this.monthName = monthName;
        this.goal = goal;
    }

    public String getId() {
        return id;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public String getMonthName() {
        return monthName;
    }

    public float getGoal() {
        return goal;
    }

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
