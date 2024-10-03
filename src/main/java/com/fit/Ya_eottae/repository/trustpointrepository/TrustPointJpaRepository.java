package com.fit.Ya_eottae.repository.trustpointrepository;

import com.fit.Ya_eottae.domain.trustpoint.TrustPoint;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@RequiredArgsConstructor
public class TrustPointJpaRepository implements TrustPointRepository{

    private final EntityManager em;

    @Override
    public TrustPoint save(TrustPoint trustPoint) {
        em.persist(trustPoint);
        return trustPoint;
    }

    @Override
    public TrustPoint findByMemberId(String memberId, long reviewId) {
        String jqpl = "select t from TrustPoint t Where t.memberId = :memberId and t.reviewId = :reviewId";
        TrustPoint trustPoint = (TrustPoint) em.createQuery(jqpl)
                .setParameter("memberId", memberId)
                .setParameter("reviewId", reviewId)
                .getSingleResult();
        return trustPoint;
    }

    @Override
    public TrustPoint findByReviewId(long reviewId) {
        TrustPoint trustPoint = em.find(TrustPoint.class, reviewId);
        return trustPoint;
    }
}
