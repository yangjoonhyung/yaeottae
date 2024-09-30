package com.fit.Ya_eottae.domain.comment;

import com.fit.Ya_eottae.domain.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter @Setter
@Entity
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentId;
    private String comment;
    private String commentDate;
    private String memberId;
    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;

    public Comment() {
    }

    public Comment(String comment) {
        this.comment = comment;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.commentDate = LocalDateTime.now().format(dateTimeFormatter);
    }

}
