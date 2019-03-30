package com.alimin.geotweet.service;

import com.alimin.geotweet.domain.Tweet;
import com.alimin.geotweet.domain.User;
import com.alimin.geotweet.repository.TweetRepository;
import com.alimin.geotweet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ali Minnet
 * @version 2.0
 */
@Service
public class Users {

    Pattern userPattern = Pattern.compile("(\\d*)\\sUT: (.*),(.*)");
    Pattern tweetPattern = Pattern.compile("(\\d*)\\s*(\\d*)\\s*(.*)");

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    @PostConstruct
    void init() throws IOException {
        //addUsers();
        //tweets();
    }

    public void addUsers() throws IOException {
        File f = new File("C:\\Users\\aliminnet\\Desktop\\yeditepe\\Database\\geoTweet\\tweets\\test_set_users.txt");

        BufferedReader b = new BufferedReader(new FileReader(f));

        String readLine = "";

        System.out.println("Reading file using Buffered Reader");

        while ((readLine = b.readLine()) != null) {
            Matcher m = userPattern.matcher(readLine);
            if(m.find()){
                userRepository.save(new User(m.group(1),new GeoJsonPoint( Double.parseDouble(m.group(2)), Double.parseDouble(m.group(3)))));
            }
        }
    }

    public void tweets() throws IOException {
        File f = new File("C:\\Users\\aliminnet\\Desktop\\yeditepe\\Database\\geoTweet\\tweets\\test_set_tweets.txt");

        BufferedReader b = new BufferedReader(new FileReader(f));

        String readLine = "";

        System.out.println("Reading file using Buffered Reader");

        while ((readLine = b.readLine()) != null) {
            Matcher m = tweetPattern.matcher(readLine);
            if(m.find()){
                tweetRepository.save(new Tweet(m.group(1),m.group(2),m.group(3)));
            }

        }
    }
}
