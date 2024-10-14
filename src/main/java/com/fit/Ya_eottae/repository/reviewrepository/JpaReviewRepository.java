package com.fit.Ya_eottae.repository.reviewrepository;

import com.fit.Ya_eottae.domain.review.Review;
import com.fit.Ya_eottae.domain.review.ReviewUpdateDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Primary
@Repository
@Transactional
@RequiredArgsConstructor
public class JpaReviewRepository implements ReviewRepository {

    private final EntityManager em;

    @Override
    public Review save(Review review) {
        em.persist(review);
        return review;
    }

    @Override
    public void deleteReview(long reviewid) {
        Review findReview = em.find(Review.class, reviewid);
        if (findReview != null) {
            em.remove(findReview);
        }
    }

    @Override
    public Review updateReview(long reviewId, ReviewUpdateDto reviewUpdateDto) {
        Review findReview = em.find(Review.class, reviewId);
        findReview.setReviewName(reviewUpdateDto.getUpdateReviewName());
        findReview.setReviewDetail(reviewUpdateDto.getUpdateReview());
        findReview.setReviewPoint(reviewUpdateDto.getUpdateReviewPoint());
        findReview.setIsAdvertisement(reviewUpdateDto.getIsAdvertisement());
        return findReview;
    }

    @Override
    public Optional<Review> findById(long reviewId) {
        Review findReview = em.find(Review.class, reviewId);
        return Optional.ofNullable(findReview);
    }

    @Override
    public List<Review> findBymemberId(String memberId) {
        String jpql = "select r from Review r Where r.member.memberId = :memberId order by r.reviewId desc";
        TypedQuery<Review> query = em.createQuery(jpql, Review.class)
                .setParameter("memberId", memberId);

        List<Review> reviewList = query.getResultList();
        return reviewList;
    }

    @Override
    public void plusTrustPoint(long reviewId) {
        Review findReview = em.find(Review.class, reviewId);
        findReview.setTrustPoint(findReview.getTrustPoint() + 1);
        em.flush();
    }

    @Override
    public void plusNoTrustPoint(long reviewId) {
        Review findReview = em.find(Review.class, reviewId);
        findReview.setNoTrustPoint(findReview.getNoTrustPoint() + 1);
        em.flush();
    }

    @Override
    public List<Review> findAllReview() {
        String jpql = "select r from Review r";
        TypedQuery<Review> query = em.createQuery(jpql, Review.class);
        List<Review> reviewList = query.getResultList();
        return reviewList;
    }

    @Override
    public List<Review> findByRestaurantId(String restaurantId) {
        String jpql = "select r from Review r Where r.restaurantId = :restaurantId";
        TypedQuery<Review> query = em.createQuery(jpql, Review.class)
                .setParameter("restaurantId", restaurantId);
        List<Review> reviewList = query.getResultList();

        return reviewList;
    }

    @Override
    public Review findBestReview() {
        String jpql = "select r From Review r ORDER BY r.trustPoint DESC";

        TypedQuery<Review> query = em.createQuery(jpql, Review.class);
        query.setMaxResults(1);
        List<Review> reviewList = query.getResultList();
        return reviewList.isEmpty() ? null : reviewList.get(0);
    }

    @Override
    public Review findSecondBestReview() {
        String jpql = "select r From Review r ORDER BY r.trustPoint DESC";

        TypedQuery<Review> query = em.createQuery(jpql, Review.class);
        query.setMaxResults(2);
        List<Review> reviewList = query.getResultList();
        return reviewList.size() < 2 ? null : reviewList.get(1);
    }


}
