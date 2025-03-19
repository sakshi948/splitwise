package com.example.splitwise.controller;

import com.example.splitwise.DTO.JoinGroupRequest;
import com.example.splitwise.service.GroupService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/join-group")
public class UserGroupController {
    private GroupService groupService;

    public UserGroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping
    public void joinGroup(@RequestBody JoinGroupRequest request) {
        this.groupService.joinGroup(request.getGroupId(), request.getUserId());
    }
}
