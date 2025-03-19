package com.example.splitwise.service;

import com.example.splitwise.models.Group;
import com.example.splitwise.models.User;
import com.example.splitwise.repository.GroupRepository;
import com.example.splitwise.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    public GroupService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository  = userRepository;
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group createGroup(Group group) {
        return groupRepository.save(group);
    }

    public User joinGroup(Integer groupId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userId));
        if (user.getGroupIds().size() > 0) {
            user.setGroupIds(user.getGroupIds().subList(0, user.getGroupIds().size()));
        }
        else {
            user.setGroupIds(new ArrayList<>());
            user.getGroupIds().add(groupId);
        }
        return userRepository.save(user);
    }

    public List<Integer> getGroupsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        return user.getGroupIds();
    }
}
