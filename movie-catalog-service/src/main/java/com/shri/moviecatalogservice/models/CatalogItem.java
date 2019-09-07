package com.shri.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZeeroIQ
 * @Date: 9/6/2019 10:45 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CatalogItem {

    private String name;
    private String desc;
    private int rating;
}
