package com.shri.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shri.moviecatalogservice.models.CatalogItem;
import com.shri.moviecatalogservice.models.Movie;
import com.shri.moviecatalogservice.models.Rating;
import com.shri.moviecatalogservice.models.UserRating;
import com.shri.moviecatalogservice.services.MovieInfo;
import com.shri.moviecatalogservice.services.UserRatingInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: ZeeroIQ
 * @Date: 9/6/2019 10:42 PM
 */
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    MovieInfo movieInfo;

    @Autowired
    UserRatingInfo userRatingInfo;

    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings = userRatingInfo.getUserRatings(userId);

        return ratings.getUserRating().stream().map(rating -> {
            return movieInfo.getCatalogItem(rating);
        })
            .collect(Collectors.toList());
    }

}
