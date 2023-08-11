package com.finalproject.kdiary.infrastructure;

import com.finalproject.kdiary.domain.Diary;
import com.finalproject.kdiary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
