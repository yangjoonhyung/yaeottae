package com.fit.Ya_eottae.repository.reviewrepository;

import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.domain.review.ReviewUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {

    Review save(Review review);

    void deleteReview(long reviewid);

    Review updateReview(long reviewId, ReviewUpdateDto reviewUpdateDto);

    Optional<Review> findById(long reviewId);

    List<Review> findBymemberId(String memberId);

    void plusTrustPoint(long reviewId);

    void plusNoTrustPoint(long reviewId);

    List<Review> findAllReview();

    List<Review> findByRestaurantId(String restaurantId);

    Review findBestReview();

    Review findSecondBestReview();

}
