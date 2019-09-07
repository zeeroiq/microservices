package com.shri.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shri.moviecatalogservice.models.Rating;
import com.shri.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @Author: ZeeroIQ
 * @Date: 9/8/2019 12:22 AM
 */
@Service
public class UserRatingInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRatings")
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        // 1. get all rated movies ID
        return restTemplate.getForObject("http://movie-rate-service/ratingsResources/users/"+ userId, UserRating.class);
    }

    public UserRating getFallbackUserRatings(@PathVariable("userId") String userId) {
        return new UserRating(userId, Arrays.asList(new Rating("0", 0)));
    }
}
