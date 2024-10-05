package com.fit.Ya_eottae.domain.AiModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewRequest {

    private String reviewDetail;

    public ReviewRequest(String reviewDetail) {
        this.reviewDetail = reviewDetail;
    }
}
