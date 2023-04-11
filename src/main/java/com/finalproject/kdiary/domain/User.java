package com.finalproject.kdiary.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    private String id = UUID.randomUUID().toString();
    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String email;
}
