package com.fit.Ya_eottae.domain.trustpoint;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TrustPoint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long isCheckId;
    private String memberId;
    private long reviewId;
    private int plusTrustPoint;
    private int minusTrustPoint;

    public TrustPoint() {
        this.plusTrustPoint = 0;
        this.minusTrustPoint = 0;
    }
}
