package com.nagarro.reviewSystem.services;

import com.nagarro.reviewSystem.CONSTANTS;
import com.nagarro.reviewSystem.model.Message;
import com.nagarro.reviewSystem.model.ReviewRequest;
import com.nagarro.reviewSystem.model.ReviewRequestStatus;
import com.nagarro.reviewSystem.model.ReviewStatus;
import com.nagarro.reviewSystem.repository.ReviewRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewRequestServiceImpl implements ReviewRequestService{

    @Autowired
    private ReviewRequestRepository reviewRequestRepository;
    @Autowired
    private CONSTANTS constants;
    @Override
    public Message request(ReviewRequest request) {
        Message result = new Message();
        if(reviewRequestRepository.findByUidAndProductId(request.getUid(), request.getProductId()).size() == 0) {
            reviewRequestRepository.save(request);
            result.setMessage("Request Sent Successfully");
            result.setStatus(constants.SUCCESS);
        } else {
            result.setMessage("Request Already Sent");
            result.setStatus(constants.FAILURE);
        }
        return result;
    }

    @Override
    public Message approve(ReviewRequest request) {
        Message result = new Message();
        if(reviewRequestRepository.existsById(request.getId())) {
            request.setStatus(constants.APPROVE);
            reviewRequestRepository.save(request);
            result.setStatus(constants.SUCCESS);
            result.setMessage("Request Approved");
        } else {
            result.setStatus(constants.FAILURE);
            result.setMessage("Invalid Request");
        }
        return result;
    }

    @Override
    public Message reject(ReviewRequest request) {
        Message result = new Message();
        if(reviewRequestRepository.existsById(request.getId())) {
            request.setStatus(constants.REJECTED);
            reviewRequestRepository.save(request);
            result.setStatus(constants.SUCCESS);
            result.setMessage("Request Rejected");
        } else {
            result.setStatus(constants.FAILURE);
            result.setMessage("Invalid Request");
        }
        return result;
    }

    @Override
    public ReviewRequestStatus getStatusById(long id) {
        ReviewRequest request = reviewRequestRepository.getReferenceById(id);
        return new ReviewRequestStatus(request.getStatus());
    }

    @Override
    public List<ReviewRequest> status(long uid) {
        return reviewRequestRepository.findByUid(uid);
    }

    @Override
    public void setRequestAsReviewed(long requestId) {
        ReviewRequest request = reviewRequestRepository.getReferenceById(requestId);
        request.setStatus(constants.REVIEWED);
        reviewRequestRepository.save(request);
    }

    @Override
    public List<ReviewRequest> getAllRequests() {
        return reviewRequestRepository.findAll();
    }

}
