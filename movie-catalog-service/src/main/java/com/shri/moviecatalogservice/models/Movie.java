package com.shri.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZeeroIQ
 * @Date: 9/6/2019 10:54 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private String movieId;
    private String name;
}
