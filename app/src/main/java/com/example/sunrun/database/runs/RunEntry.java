package com.example.sunrun.database.runs;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.time.LocalDate;


public class RunEntry  {

    private String id;

    private LocalDate date;

    private String distance;

    private String time;

    public RunEntry(String id, LocalDate date, String distance, String time) {
        this.id = id;
        this.date = date;
        this.distance = distance;
        this.time = time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate  getDate() {
        return date;
    }

    public void setDate(LocalDate  date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public int getMonthFromDate() {
        // Assuming getDate() returns a LocalDate
        LocalDate date = getDate();

        // Extract the month from the date
        return date.getMonthValue();
    }
}
