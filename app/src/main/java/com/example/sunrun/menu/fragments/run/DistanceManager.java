package com.example.sunrun.menu.fragments.run;

import android.location.Location;

public class DistanceManager {

    private static DistanceManager instance;
    private boolean isTrackingDistance;
    private double totalDistance;

    private Location lastLocation;

    private DistanceManager() {
        totalDistance = 0;
    }

    public static DistanceManager getInstance() {
        if (instance == null) {
            instance = new DistanceManager();
        }
        return instance;
    }

    public void startTrackingDistance() {
        isTrackingDistance = true;
        totalDistance = 0;
    }

    public void stopTrackingDistance() {
        isTrackingDistance = false;
    }

    public void updateDistance(Location newLocation) {
        if (isTrackingDistance && lastLocation != null) {
            float distance = lastLocation.distanceTo(newLocation);
            totalDistance += distance / 1000;
        }

        lastLocation = newLocation;
    }

    public double getTotalDistance() {
        return totalDistance;
    }
}
