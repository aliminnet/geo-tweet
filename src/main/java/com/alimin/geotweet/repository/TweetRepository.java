package com.alimin.geotweet.repository;

import com.alimin.geotweet.domain.Tweet;
import com.alimin.geotweet.domain.User;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public interface TweetRepository extends MongoRepository<Tweet, String> {

    public List<Tweet> findByUserId(@Param("userId") String userId, Pageable pageable);

    public List<Tweet> findByUserIdAndTextContaining(@Param("userId") String userId,@Param("text") String text, Pageable pageable);
}