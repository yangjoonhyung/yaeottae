package com.fit.Ya_eottae.web.review.service;

import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.domain.trustpoint.TrustPoint;
import com.fit.Ya_eottae.repository.reviewrepository.ReviewRepository;
import com.fit.Ya_eottae.repository.trustpointrepository.TrustPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final TrustPointRepository trustPointRepository;

    public String calculateBayesianAverage(Review review, List<Review> allReviews) {
        // 좋아요와 싫어요 수
        int trustPoint = review.getTrustPoint(); // 좋아요 갯수
        int noTrustPoint = review.getNoTrustPoint(); // 싫어요 갯수
        int v = trustPoint + noTrustPoint; // 참여자 수 (좋아요 + 싫어요)

        if (v <= 20) {
            return "...";
        }

        // 전체 리뷰 신뢰도 점수 합과 평균 점수 계산
        int allPoint = 0;
        for (Review review1 : allReviews) {
            allPoint += review1.getTrustPoint();  // 모든 리뷰의 좋아요 갯수 합
        }

        // 전체 리뷰의 평균 점수 C 계산
        double C = (double) allPoint / allReviews.size(); // 모든 리뷰 좋아요 평균

        // 임계 참여자 수 (thresHold)
        int m = 10; // 예를 들어, 임계값을 10으로 설정

        // 실제 점수 R (좋아요 비율, 좋아요 / 참여자 수)
        double R = (v > 0) ? (double) trustPoint / v : 0.0;

        // 베이지안 평균 계산
        double adjustedScore = (v * R + m * C) / (v + m);

        String reviewTrustScore = String.valueOf(adjustedScore);

        return reviewTrustScore;
    }

    public float avgReviewPoint() {
        List<Review> allReview = reviewRepository.findAllReview();

        int reviewSize = allReview.size();
        int sum = 0;

        for (Review findReview : allReview) {
            sum += findReview.getReviewPoint();
        }

        float avg = (float) sum / reviewSize;
        return avg;
    }

    public float avgOneReviewPoint(String restaurantId) {
        List<Review> reviewList = reviewRepository.findByRestaurantId(restaurantId);

        if (reviewList == null || reviewList.isEmpty()) {
            return 0;
        }

        int reviewSize = reviewList.size();
        int sum = 0;

        for (Review findReview : reviewList) {
            sum += findReview.getReviewPoint();
        }

        float avg = (float) sum / reviewSize;
        avg = Math.round(avg * 10) / 10.0f;
        return avg;
    }

    public Boolean isOkToCheckTrustPoint(long reviewId, String memberId) {
        TrustPoint memberTrustPoint = trustPointRepository.findByMemberId(memberId, reviewId);

        if (memberTrustPoint.getPlusTrustPoint() >= 1 && memberTrustPoint.getMemberId().equals(memberId)) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean isOkToCheckNoTrustPoint(long reviewId, String memberId) {
        TrustPoint memberTrustPoint = trustPointRepository.findByMemberId(memberId, reviewId);

        if (memberTrustPoint.getMinusTrustPoint() >= 1 && memberTrustPoint.getMemberId().equals(memberId)) {
            return false;
        } else {
            return true;
        }
    }
}
