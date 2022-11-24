package com.codecool.byteMe.dao;

import com.codecool.byteMe.model.postable.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);

    List<Post> findByUser_IdInAndGroupIdIsNull(Collection<Long> userIds);

    List<Post> findByUserIdAndGroupIdIsNull(Long userId);
}
