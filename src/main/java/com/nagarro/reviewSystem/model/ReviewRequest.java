package com.nagarro.reviewSystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long uid;
    private int status;
    private long productId;

    public ReviewRequest(long uid, long productId) {
        this.uid = uid;
        this.productId = productId;
        this.status = 0;
    }

    public ReviewRequest(long uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "ReviewRequest{" +
                "id=" + id +
                ", uid=" + uid +
                ", status=" + status +
                ", productId=" + productId +
                '}';
    }
}
