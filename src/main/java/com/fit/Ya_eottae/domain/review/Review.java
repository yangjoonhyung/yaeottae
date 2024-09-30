package com.fit.Ya_eottae.domain.review;

import com.fit.Ya_eottae.domain.member.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
@Entity
public class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    @NotBlank
    private String reviewName;
    @NotBlank
    private String reviewDetail;
    private int reviewPoint;
    private int trustPoint;
    private int noTrustPoint;
    private String isAdvertisement;
    private String writeDate;
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
    private long restaurantId;
    private String trustScore;

    public Review() {
    }

    public Review(String reviewName, String reviewDetail, int reviewPoint, String isAdvertisement, long restaurantId) {
        this.reviewName = reviewName;
        this.reviewDetail = reviewDetail;
        this.reviewPoint = reviewPoint;
        this.trustPoint = 0;
        this.noTrustPoint = 0;
        this.isAdvertisement = isAdvertisement;
        this.trustScore = "-";
        this.restaurantId = restaurantId;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.writeDate = LocalDateTime.now().format(dateTimeFormatter);
    }

}
