package com.fit.Ya_eottae.repository.reviewrepository;

import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.domain.review.ReviewUpdateDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

//@Repository
public class MemoryReviewRepository implements ReviewRepository{

    private static Map<Long, Review> reviewStore = new ConcurrentHashMap<>();
    private static AtomicLong reviewId = new AtomicLong(0);

    @Override
    public Review save(Review review) {
        long saveReviewId = reviewId.incrementAndGet();
        review.setReviewId(saveReviewId);
        reviewStore.put(review.getReviewId(), review);
        return review;
    }

    @Override
    public void deleteReview(long reviewId) {
        Review removeReview = reviewStore.remove(reviewId);
    }

    @Override
    public Review updateReview(long reviewId, ReviewUpdateDto reviewUpdateDto) {
        Review findReview = findById(reviewId).orElseThrow();
        findReview.setReviewName(reviewUpdateDto.getUpdateReviewName());
        findReview.setReviewDetail(reviewUpdateDto.getUpdateReview());
        findReview.setReviewPoint(reviewUpdateDto.getUpdateReviewPoint());
        return findReview;
    }

    @Override
    public Optional<Review> findById(long reviewId) {
        Optional<Review> review = Optional.ofNullable(reviewStore.get(reviewId));
        return review;
    }

    @Override
    public List<Review> findBymemberId(String memberId) {
        List<Review> allReview = findAllReview();
        List<Review> reviewList = allReview.stream().filter(review -> review.getMember().getMemberId().equals(memberId)).collect(Collectors.toList());
        return reviewList;
    }

    @Override
    public void plusTrustPoint(long reviewId) {
        Review review = findById(reviewId).orElseThrow();
        review.setTrustPoint(review.getTrustPoint() + 1);
    }

    @Override
    public void plusNoTrustPoint(long reviewId) {
        Review review = findById(reviewId).orElseThrow();
        review.setNoTrustPoint(review.getNoTrustPoint() + 1);
    }

    @Override
    public List<Review> findAllReview() {
        ArrayList<Review> reviews = new ArrayList<>(reviewStore.values());
        return reviews;
    }

    @Override
    public List<Review> findByRestaurantId(String restaurantId) {
        return List.of();
    }

    @Override
    public Review findBestReview() {
        return null;
    }

    @Override
    public Review findSecondBestReview() {
        return null;
    }
}
