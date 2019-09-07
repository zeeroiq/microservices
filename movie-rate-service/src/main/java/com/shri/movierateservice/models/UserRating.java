package com.shri.movierateservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
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

    public void initData(String userId) {
        this.setUserId(userId);
        this.setUserRating(Arrays.asList(
                new Rating("100", 3),
                new Rating("200", 4)
        ));
    }
}
