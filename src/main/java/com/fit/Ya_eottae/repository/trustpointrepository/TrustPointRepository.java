package com.fit.Ya_eottae.repository.trustpointrepository;

import com.fit.Ya_eottae.domain.trustpoint.TrustPoint;

public interface TrustPointRepository {

    public TrustPoint save(TrustPoint trustPoint);
    public TrustPoint findByMemberId(String memberId, long reviewId);
    public TrustPoint findByReviewId(long review);
}
