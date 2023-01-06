package com.codecool.byteMe.controller;

import com.codecool.byteMe.model.Group;
import com.codecool.byteMe.model.postable.Post;
import com.codecool.byteMe.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin(origins = "http://localhost:3000")
public class GroupController {

    private GroupService groupService;

    @Autowired
    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("all")
    public List<Group> getAllGroup() {
        return groupService.getAll();
    }

    @PostMapping("add")
    public Group addGroup(@RequestBody Group group) {
        return groupService.add(group);
    }

    @PutMapping("edit")
    public void editGroup(@RequestBody Group group) {
        groupService.edit(group);
    }

    @GetMapping("{groupId}")
    public Group findById(@PathVariable Long groupId) {
        return groupService.findById(groupId);
    }

    @GetMapping("user/{userId}")
    public List<Group> findByUserId(@PathVariable Long userId) {
        return groupService.findByUserId(userId);
    }

    @DeleteMapping("delete/{groupId}")
    public void deleteGroup(@PathVariable Long groupId) {
        groupService.delete(groupId);
    }

    @PostMapping("post/add/{groupId}")
    public Group addPostToGroup(@RequestBody Post post, @PathVariable Long groupId) {
        System.out.println(post);
        System.out.println(groupId);
        return groupService.addPostToGroup(post, groupId);
    }
}
