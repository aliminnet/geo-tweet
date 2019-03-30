package com.alimin.geotweet.domain;

import java.util.List;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public class ResponseItem {

    User user;
    List<Tweet> tweets;

    public ResponseItem(User user, List<Tweet> tweets) {
        this.user = user;
        this.tweets = tweets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(List<Tweet> tweets) {
        this.tweets = tweets;
    }
}
