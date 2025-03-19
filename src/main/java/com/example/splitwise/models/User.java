package com.example.splitwise.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    @ElementCollection
    @CollectionTable(name = "user_group_ids", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "group_id")
    private List<Integer> groupIds = new ArrayList<>();
}
