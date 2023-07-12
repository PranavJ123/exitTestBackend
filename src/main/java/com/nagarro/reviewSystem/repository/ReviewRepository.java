package com.nagarro.reviewSystem.repository;

import com.nagarro.reviewSystem.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    public List<Review> findByUid(long uid);

    public List<Review> findByProductId(long productId);

    public boolean existsByUidAndProductId(long uid, long productId);

    public List<Review> findById(long id);


}
