package com.codecool.byteMe.dao.mem;

import com.codecool.byteMe.dao.CommentDao;
import com.codecool.byteMe.dao.PostDao;
import com.codecool.byteMe.model.postable.Comment;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CommentDaoMem implements CommentDao {
    private PostDao postDao;

    public CommentDaoMem() {
        this.postDao = new PostDaoMem();
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Set<Comment> getAll() {
        return postDao.getAll()
                .stream()
                .map(Post::getComments)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Comment add(Comment comment) {
        if (comment.getPostId() == null && comment.getUserId() == null)
            throw new IllegalArgumentException("Missing object attribute: userId and postId can't be null.");
        return postDao.getAll()
                .stream()
                .filter(post -> post.getId().equals(comment.getPostId()))
                .findFirst().orElseThrow(NoSuchElementException::new)
                .addComment(comment);

    }

    @Override
    public Comment findById(UUID commentId) {
        return getAll()
                .stream()
                .filter(comment -> comment.getId().equals(commentId))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<Comment> findByUserId(UUID userId) {
        return getAll()
                .stream()
                .filter(comment -> comment.getUserId().equals(userId))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Comment> findByPostId(UUID postId) {
        return getAll()
                .stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toSet());
    }

    @Override
    public Comment edit(Comment comment) {
        return findById(comment.getId()).editComment(comment);
    }

    @Override
    public Comment delete(UUID commentId) {
        Comment commentToDelete = findById(commentId);
        postDao.findById(commentToDelete.getPostId())
                .deleteComment(commentToDelete);
        return commentToDelete;
    }
}
