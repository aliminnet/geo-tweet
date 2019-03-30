package com.alimin.geotweet.repository;

import com.alimin.geotweet.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Polygon;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Ali Minnet
 * @version 2.0
 */
public interface  UserRepository extends MongoRepository<User, String> {
    List<User> findByLocationWithin(Polygon polygon,Pageable pageable);
}