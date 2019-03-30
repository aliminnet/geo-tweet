package com.alimin.geotweet.controller;

import com.alimin.geotweet.domain.ResponseItem;
import com.alimin.geotweet.domain.Tweet;
import com.alimin.geotweet.domain.User;
import com.alimin.geotweet.repository.TweetRepository;
import com.alimin.geotweet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


/**
 * @author Ali Minnet
 * @version 2.0
 */
@RestController
public class GeoTweetController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TweetRepository tweetRepository;

    @RequestMapping(value="/users",  method=RequestMethod.GET)
    public List<User> getUsers() {
        List<User> results = userRepository.findAll();
        return results.subList(0, 5);
    }

    @RequestMapping(value="/tweets",  method=RequestMethod.GET)
    public List<Tweet> getTweets() {
        //Page<Tweet> results = tweetRepository.findAll(new PageRequest(0, 10));
        List<Tweet> results = tweetRepository.findAll();
        return results;
    }

    @RequestMapping(value="/tweetsByUserId",  method=RequestMethod.GET)
    public List<Tweet> tweetsByUserId(@RequestParam(name="userId") String userId,@RequestParam(name="keyword", required = false) String keyword) {

        List<Tweet> results = new ArrayList<>();
        if(StringUtils.isEmpty(keyword)){
            results = tweetRepository.findByUserId(userId,new PageRequest(0, 10));
        }else{
            results = tweetRepository.findByUserIdAndTextContaining(userId,keyword,new PageRequest(0, 10));
        }
        return results;
    }

    @RequestMapping(value="/findByLocationWithin",  method=RequestMethod.GET)
    public List<User> findByLocationWithin(
            @RequestParam(name="x") Point x,
            @RequestParam(name="y") Point y,
            @RequestParam(name="z") Point z,
            @RequestParam(name="t") Point t) {
       // Polygon bounds = new Polygon(new Point(118,-71), new Point(45,28), new Point(45,-71), new Point(118,28));
        Polygon bounds = new Polygon(x,y,z,t);
        List<User> results = userRepository.findByLocationWithin(bounds,new PageRequest(0, 10));
        return results;
    }

    @RequestMapping(value="/findTweetsByLocationWithin",  method=RequestMethod.GET)
    public  List<ResponseItem> findTweetsByLocationWithin(
            @RequestParam(name="x") Point x,
            @RequestParam(name="y") Point y,
            @RequestParam(name="z") Point z,
            @RequestParam(name="t") Point t,
            @RequestParam(name="keyword", required = false) String keyword) {

        Polygon bounds = new Polygon(x,y,z,t);
        List<User> results = userRepository.findByLocationWithin(bounds,new PageRequest(0, 200));

        List<ResponseItem> responseItems = new ArrayList<>();

        for (User user:results) {
            if(StringUtils.isEmpty(keyword)){
                responseItems.add(new ResponseItem(user,tweetRepository.findByUserId(user.getTwitterID(),new PageRequest(0, 10))));
            }else{
                responseItems.add(new ResponseItem(user,tweetRepository.findByUserIdAndTextContaining(user.getTwitterID(),keyword,new PageRequest(0, 10))));
            }

        }

        return responseItems;
    }



}
