package com.alimin.geotweet.domain;

import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public class User {
    String twitterID;

    GeoJsonPoint location;

    public User(String twitterID, GeoJsonPoint location) {
        this.twitterID = twitterID;
        this.location = location;
    }


    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public String getTwitterID() {
        return twitterID;
    }

    public void setTwitterID(String twitterID) {
        this.twitterID = twitterID;
    }
}
