package com.shri.movieinfoservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZeeroIQ
 * @Date: 9/7/2019 6:49 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieSummary {

    private String id;
    private String title;
    private String overview;
}
