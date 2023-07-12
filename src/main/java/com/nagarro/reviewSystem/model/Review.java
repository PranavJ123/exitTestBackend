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
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long uid;
    private String message;
    private float ratings;
    private long productId;
    private String heading;
    private int status;

    public Review(long uid, String message, float ratings, long productId, String heading) {
        this.uid = uid;
        this.message = message;
        this.ratings = ratings;
        this.productId = productId;
        this.heading = heading;
    }

    public Review(long productId) {
        this.productId = productId;
    }


    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", uid=" + uid +
                ", message='" + message + '\'' +
                ", ratings=" + ratings +
                ", productId=" + productId +
                ", heading='" + heading + '\'' +
                ", status=" + status +
                '}';
    }
}
