package com.alimin.geotweet.domain;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public class Tweet {
    String userId;
    String tweetId;
    String text;

    public Tweet(String userId, String tweetId, String text) {
        this.userId = userId;
        this.tweetId = tweetId;
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTweetId() {
        return tweetId;
    }

    public void setTweetId(String tweetId) {
        this.tweetId = tweetId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
