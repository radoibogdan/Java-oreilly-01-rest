package com.oreilly.beginner.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Ken Kousen on 11/1/16.
 */
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Geometry {
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "location=" + location +
                '}';
    }
}