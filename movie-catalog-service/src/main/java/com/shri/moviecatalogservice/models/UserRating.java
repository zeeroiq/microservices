package com.shri.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: ZeeroIQ
 * @Date: 9/7/2019 12:33 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {

    private String userId;
    private List<Rating> userRating;

}
