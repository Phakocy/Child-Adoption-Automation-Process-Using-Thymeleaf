package com.interswitch.academy.adoptionautomationsystem.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

        @Id
        private String id;
        @Column(nullable = false)
        private String name;
        @Column(nullable = false, unique = true)
        private String email;
        @Column(nullable = false)
        private String password;
        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(
                name = "users_roles",
                joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")}
        )
        private List<Role> roles = new ArrayList<>();
    }

