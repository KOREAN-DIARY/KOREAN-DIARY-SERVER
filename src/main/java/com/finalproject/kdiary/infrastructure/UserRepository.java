package com.finalproject.kdiary.infrastructure;

import com.finalproject.kdiary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
