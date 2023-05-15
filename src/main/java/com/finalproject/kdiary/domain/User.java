package com.finalproject.kdiary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    private final String id = UUID.randomUUID().toString();

    @Email
    private String email;

    @Column(nullable = false)
    private String name;

    @Builder
    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

}
