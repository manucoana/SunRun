package com.example.sunrun.database.runs;

import java.time.LocalDate;


public class RunEntry {

    private String id;

    private LocalDate date;

    private final String distance;

    private final String time;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDistance() {
        return distance;
    }

    public String getTime() {
        return time;
    }

}
