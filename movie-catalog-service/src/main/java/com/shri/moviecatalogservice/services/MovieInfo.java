package com.shri.moviecatalogservice.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.shri.moviecatalogservice.models.CatalogItem;
import com.shri.moviecatalogservice.models.Movie;
import com.shri.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author: ZeeroIQ
 * @Date: 9/8/2019 12:21 AM
 */
@Service
public class MovieInfo {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalog")
    public CatalogItem getCatalogItem(Rating rating) {
        // 2. for each movie ID, call movie info service and get Details
        Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
        // 3. put them all together
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }

    public CatalogItem getFallbackCatalog(Rating rating) {
        return new CatalogItem("Movie not found", "", rating.getRating());
    }
}
