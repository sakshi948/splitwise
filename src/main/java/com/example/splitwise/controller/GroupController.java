package com.example.splitwise.controller;

import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.service.GroupService;
import com.example.splitwise.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/create-group")
    public Group createUser(@RequestBody Group group) {
        return groupService.createGroup(group);
    }
}
