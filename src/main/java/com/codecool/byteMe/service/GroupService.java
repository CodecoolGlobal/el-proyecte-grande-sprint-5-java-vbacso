package com.codecool.byteMe.service;

import com.codecool.byteMe.dao.GroupRepository;
import com.codecool.byteMe.dao.PostRepository;
import com.codecool.byteMe.model.Group;
import com.codecool.byteMe.model.postable.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service("groupService")
public class GroupService {

    private GroupRepository groupRepository;
    private PostRepository postRepository;

    @Autowired
    public GroupService(GroupRepository groupRepository, PostRepository postRepository) {
        this.groupRepository = groupRepository;
        this.postRepository = postRepository;
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group add(Group group) {
        return groupRepository.save(group);
    }

    public Group findById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() -> new NoSuchElementException("No group with given id"));
    }

    public List<Group> findByUserId(Long userId) {
        return groupRepository.findByMembers_Id(userId);
    }

    public Group edit(Group group) {
        return groupRepository.save(group);
    }

    public void delete(Long groupId) {
        groupRepository.deleteById(groupId);
    }

    public Group addPostToGroup(Post post, Long groupId) {
        Post groupPost = postRepository.save(post);
        Group group = findById(groupId);
        group.setPosts(List.of(groupPost));
        return groupRepository.save(group);
    }
}
