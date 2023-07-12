package com.nagarro.reviewSystem.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stats {

    private long totalUsers;
    private long totalProducts;
    private long totalReviews;

}
