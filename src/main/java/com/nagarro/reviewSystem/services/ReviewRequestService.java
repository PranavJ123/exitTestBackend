package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.model.Message;
import com.nagarro.reviewSystem.model.ReviewRequest;
import com.nagarro.reviewSystem.model.ReviewRequestStatus;
import com.nagarro.reviewSystem.model.ReviewStatus;

import java.util.List;

public interface ReviewRequestService {

    public Message request(ReviewRequest request);

    public Message approve(ReviewRequest request);

    public Message reject(ReviewRequest request);

    public ReviewRequestStatus getStatusById(long id);

    public List<ReviewRequest> status(long uid);
    public void setRequestAsReviewed(long requestId);
    public List<ReviewRequest> getAllRequests();

}
