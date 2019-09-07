package com.shri.moviecatalogservice.resources;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shri.moviecatalogservice.models.CatalogItem;
import com.shri.moviecatalogservice.models.Movie;
import com.shri.moviecatalogservice.models.Rating;
import com.shri.moviecatalogservice.models.UserRating;
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

    @GetMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        UserRating ratings = getUserRatings(userId);

        return ratings.getUserRating().stream().map(rating -> {
            return getCatalogItem(rating);
        })
            .collect(Collectors.toList());
    }

    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    private CatalogItem getCatalogItem(Rating rating) {
        // 2. for each movie ID, call movie info service and get Details
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        // 3. put them all together
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    private CatalogItem getFallbackCatalog(Rating rating) {
        return new CatalogItem("Movie not found", "", rating.getRating());
    }

    @HystrixCommand(fallbackMethod = "getFallbackUserRatings")
    private UserRating getUserRatings(@PathVariable("userId") String userId) {
        // 1. get all rated movies ID
        return restTemplate.getForObject("http://movie-rate-service/ratingsResources/users/"+ userId, UserRating.class);
    }

    private UserRating getFallbackUserRatings(@PathVariable("userId") String userId) {
        return new UserRating(userId, Arrays.asList(new Rating("0", 0)));
    }

    public List<CatalogItem> getFallbackCatalog(@PathVariable("userId") String userId) {
        return Arrays.asList(new CatalogItem("No movie", "", 0));
    }
}
