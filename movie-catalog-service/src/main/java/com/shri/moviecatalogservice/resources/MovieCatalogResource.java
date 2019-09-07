package com.shri.moviecatalogservice.resources;

import com.shri.moviecatalogservice.models.CatalogItem;
import com.shri.moviecatalogservice.models.Movie;
import com.shri.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
        // 1. get all rated movies ID
        UserRating ratings = restTemplate.getForObject("http://movie-rate-service/ratingsResources/users/"+ userId, UserRating.class);

        return ratings.getUserRating().stream().map(rating -> {
            // 2. for each movie ID, call movie info service and get Details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
            // 3. put them all together
            return new CatalogItem(movie.getName(), "Successor of Infinity War", rating.getRating());
            })
            .collect(Collectors.toList());
    }
}
