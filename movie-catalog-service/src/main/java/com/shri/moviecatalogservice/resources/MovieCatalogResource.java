package com.shri.moviecatalogservice.resources;

import com.shri.moviecatalogservice.models.CatalogItem;
import com.shri.moviecatalogservice.models.Movie;
import com.shri.moviecatalogservice.models.Rating;
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
    public List<CatalogItem> getCatalog(@PathVariable("userId") String item) {
        // 1. get all rated movies ID
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 3),
                new Rating("321", 5)
        );

        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
            return new CatalogItem(movie.getName(), "Successor of Infinity War", rating.getRating());
            }).collect(Collectors.toList());

        // 2. for each movie ID, call movie info service and get Details
        // 3. put them all together
//        return Collections.singletonList(
//                new CatalogItem("Endgame", "Successor of Infinity war", 5)
//        );
    }
}
