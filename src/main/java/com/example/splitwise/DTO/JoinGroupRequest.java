package com.example.splitwise.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class JoinGroupRequest {
    private Integer groupId;
    private Long userId;
}