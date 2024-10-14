package com.fit.Ya_eottae.domain.review;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewUpdateDto {

    @NotBlank
    private String updateReviewName;
    @NotBlank
    private String updateReview;
    @NotBlank
    private int updateReviewPoint;
    private String isAdvertisement;

    public ReviewUpdateDto(String updateReviewName, String updateReview, int updateReviewPoint, String isAdvertisement) {
        this.updateReviewName = updateReviewName;
        this.updateReview = updateReview;
        this.updateReviewPoint = updateReviewPoint;
        this.isAdvertisement = isAdvertisement;
    }
}
