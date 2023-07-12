package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.CONSTANTS;
import com.nagarro.reviewSystem.model.*;
import com.nagarro.reviewSystem.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewRequestService reviewRequestService;
    @Autowired
    private CONSTANTS constants;



    @Override
    public Message request(ReviewUpdateRequest review) {
        Message result = new Message();
        ReviewRequestStatus requestStatus = reviewRequestService.getStatusById(review.getRequestId());
        if(requestStatus.getStatus() == constants.APPROVE) {
            if(!isReviewExists(review.getReview().getUid(), review.getReview().getProductId())) {
                reviewRepository.save(review.getReview());
                result.setMessage("Review sent for approval");
                result.setStatus(constants.SUCCESS);
                reviewRequestService.setRequestAsReviewed(review.getRequestId());
            } else {
                result.setMessage("Review Already Sent");
                result.setStatus(constants.FAILURE);
            }
        } else if(requestStatus.getStatus() == constants.PENDING) {
            result.setStatus(constants.FAILURE);
            result.setMessage("Review Request is still Pending");
        } else {
            result.setMessage("Review Request has been rejected");
            result.setStatus(constants.FAILURE);
        }
        return result;
    }

    private boolean isReviewExists(long uid, long productId) {
        return reviewRepository.existsByUidAndProductId(uid, productId);
    }

    @Override
    public Message approve(Review review) {
        System.out.println(review);
        Message res = new Message();
        if(reviewRepository.existsById(review.getId()) && review.getStatus() != constants.REJECTED) {
            review.setStatus(constants.APPROVE);
            reviewRepository.save(review);
            res.setStatus(constants.SUCCESS);
            res.setMessage("Review Approved");
        } else {
            res.setStatus(constants.FAILURE);
            res.setMessage("Request Failed");
        }
        return res;
    }

    @Override
    public Message reject(Review review) {
        System.out.println(review);
        Message res = new Message();
        if(reviewRepository.existsById(review.getId()) && review.getStatus() != constants.APPROVE) {
            review.setStatus(constants.REJECTED);
            reviewRepository.save(review);
            res.setStatus(constants.SUCCESS);
            res.setMessage("Review Rejected");
        } else {
            res.setStatus(constants.FAILURE);
            res.setMessage("Request Failed");
        }
        return res;
    }

    @Override
    public ReviewStatus getStatusById(long id) {
        Review review = reviewRepository.getReferenceById(id);
        return new ReviewStatus(review.getStatus());
    }

    @Override
    public List<Review> status(long uid) {
        return reviewRepository.findByUid(uid);
    }

    @Override
    public long totalReviews() {
        return reviewRepository.count();
    }

    @Override
    public List<Review> getReviewsByProductId(long productId) {
        return reviewRepository.findByProductId(productId);
    }

    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Review getReviewById(long id) {
        return reviewRepository.findById(id).get(0);
    }


}
