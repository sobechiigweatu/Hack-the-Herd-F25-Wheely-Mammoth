package com.wheelymammoth.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Review {

    private String reviewId;
    private String reviewerId;
    private String reviewedUserId;
    private String rideId;
    private int rating;
    private String comment;
    private LocalDateTime timestamp;

    public Review() {}

    public Review(String reviewerId, String reviewedUserId,
                  String rideId, int rating, String comment) {
        this.reviewId = UUID.randomUUID().toString();
        this.reviewerId = reviewerId;
        this.reviewedUserId = reviewedUserId;
        this.rideId = rideId;
        this.rating = rating;
        this.comment = comment;
        this.timestamp = LocalDateTime.now();
    }

    public String getReviewId() { return reviewId; }
    public String getReviewerId() { return reviewerId; }
    public String getReviewedUserId() { return reviewedUserId; }
    public String getRideId() { return rideId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getTimestamp() { return timestamp; }
}