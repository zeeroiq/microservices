package com.shri.movierateservice.models;

import lombok.Data;

import java.util.List;

/**
 * @Author: ZeeroIQ
 * @Date: 9/7/2019 12:33 PM
 */
@Data
public class UserRating {

    private List<Rating> userRating;
}
