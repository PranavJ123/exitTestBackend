package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.model.*;

import java.util.List;

public interface ReviewService {
    public Message request(ReviewUpdateRequest review);

    public Message approve(Review review);

    public Message reject(Review review);

    public ReviewStatus getStatusById(long id);

    public List<Review> status(long uid);

    public long totalReviews();

    public List<Review> getReviewsByProductId(long productId);
    public List<Review> getAllReviews();

    public Review getReviewById(long id);

}
