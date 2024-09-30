package com.fit.Ya_eottae.repository.commentrepository;

import com.fit.Ya_eottae.domain.comment.Comment;
import com.fit.Ya_eottae.domain.review.Review;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);

    List<Comment> findByReviewId(long reviewId);

    void deleteComment(long commentId);

    Optional<Comment> findById(long commentId);

    List<Comment> findAllComment();
}
