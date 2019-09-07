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
import org.springframework.web.reactive.function.client.WebClient;

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
    private WebClient.Builder webClientBuilder;


    @GetMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String item) {
    // 1. get all rated movies ID
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 3),
                new Rating("321", 5)
        );

        return ratings.stream().map(rating -> {
            Movie movie = webClientBuilder.build() // builder pattern will give you a client
                    .get() // tells i'm going to do a get call
                    .uri("http://localhost:8081/movies/" + rating.getMovieId()) // specifies the uri
                    .retrieve() // tells to spring to go to the fetch
                    .bodyToMono(Movie.class) // tells whatever you get into the body part convert it into the instance of Movie class. here mono is kind of a promise that in future you are going to get this/use this which is an ASYNCHRONOUS call
                    .block();// since initially mono will return a empty body and we have promised to return a list of CatalogItems we need to wait until something is there in the body. this thing is done here by the block() call

            return new CatalogItem(movie.getName(), "Successor of Infinity War", rating.getRating());
            }).collect(Collectors.toList());

        // 2. for each movie ID, call movie info service and get Details
        // 3. put them all together
//        return Collections.singletonList(
//                new CatalogItem("Endgame", "Successor of Infinity war", 5)
//        );
    }
}
