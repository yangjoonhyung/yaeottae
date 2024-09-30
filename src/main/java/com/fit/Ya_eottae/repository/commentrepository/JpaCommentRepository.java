package com.fit.Ya_eottae.repository.commentrepository;

import com.fit.Ya_eottae.domain.comment.Comment;
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
public class JpaCommentRepository implements CommentRepository {

    private final EntityManager em;

    @Override
    public Comment save(Comment comment) {
        em.persist(comment);
        return comment;
    }

    @Override
    public List<Comment> findByReviewId(long reviewId) {
        String jpql = "select c from Comment c Where c.review.reviewId = :reviewId";
        TypedQuery<Comment> query = em.createQuery(jpql, Comment.class)
                .setParameter("reviewId", reviewId);

        List<Comment> commentList = query.getResultList();
        return commentList;
    }

    @Override
    public void deleteComment(long commentId) {
        Comment findComment = em.find(Comment.class, commentId);
        em.remove(findComment);
    }

    @Override
    public Optional<Comment> findById(long commentId) {
        Comment findComment = em.find(Comment.class, commentId);
        return Optional.ofNullable(findComment);
    }

    @Override
    public List<Comment> findAllComment() {
        String jpql = "select c from Comment c";
        TypedQuery<Comment> query = em.createQuery(jpql, Comment.class);
        List<Comment> commentList = query.getResultList();
        return commentList;
    }
}
