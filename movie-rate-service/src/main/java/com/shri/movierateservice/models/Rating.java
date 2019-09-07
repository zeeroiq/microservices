package com.shri.movierateservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZeeroIQ
 * @Date: 9/6/2019 11:11 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rating {

    private String movieId;
    private int rating;
}
