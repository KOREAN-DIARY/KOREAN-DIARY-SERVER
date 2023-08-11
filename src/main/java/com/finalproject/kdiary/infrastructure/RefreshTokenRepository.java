package com.finalproject.kdiary.infrastructure;


import org.springframework.data.repository.CrudRepository;
import com.finalproject.kdiary.domain.RefreshToken;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
