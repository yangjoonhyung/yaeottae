package com.fit.Ya_eottae.repository.commentrepository;

import com.fit.Ya_eottae.domain.comment.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

//@Repository
public class MemoryCommentRepositorry implements CommentRepository {

    private static Map<Long, Comment> commentStore = new ConcurrentHashMap<>();
    private static AtomicLong commentId = new AtomicLong(0);

    @Override
    public Comment save(Comment comment) {
        comment.setCommentId(commentId.incrementAndGet());
        commentStore.put(comment.getCommentId(), comment);
        return comment;
    }

    @Override
    public List<Comment> findByReviewId(long reviewId) {
        List<Comment> allComment = findAllComment();
        return allComment.stream().filter(comment -> comment.getReview().getReviewId() == reviewId).collect(Collectors.toList());
    }

    @Override
    public void deleteComment(long commentId) {
        Comment findComment = findById(commentId).orElseThrow();
        commentStore.remove(findComment.getCommentId());
    }

    @Override
    public Optional<Comment> findById(long commentId) {
        return Optional.of(commentStore.get(commentId));
    }

    @Override
    public List<Comment> findAllComment() {
        return new ArrayList<>(commentStore.values());
    }
}
