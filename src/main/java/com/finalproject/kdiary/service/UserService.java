package com.finalproject.kdiary.service;

import com.finalproject.kdiary.config.jwt.JwtService;
import com.finalproject.kdiary.controller.user.dto.request.UserCreateRequestDto;
import com.finalproject.kdiary.controller.user.dto.request.UserLoginRequestDto;
import com.finalproject.kdiary.controller.user.dto.response.UserCreateResponseDto;
import com.finalproject.kdiary.domain.RefreshToken;
import com.finalproject.kdiary.domain.User;
import com.finalproject.kdiary.exception.ErrorStatus;
import com.finalproject.kdiary.exception.model.*;
import com.finalproject.kdiary.infrastructure.RefreshTokenRepository;
import com.finalproject.kdiary.infrastructure.UserRepository;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;

@Service
public class UserService {

    private static final String SECRET_KEY = "abcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
    private static final int ACCESS_TOKEN_EXPIRES = 30000;
    private final SecretKey secretKey;

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    public UserService(final UserRepository userRepository,
                       final RefreshTokenRepository refreshTokenRepository, final JwtService jwtService) {
        this.userRepository = userRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
        this.secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    @Transactional
    public UserCreateResponseDto create(UserCreateRequestDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        userRepository.save(user);

        return UserCreateResponseDto.of(user.getId(), user.getEmail(), user.getName());

    }

    @Transactional
    public String login(UserLoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new NotFoundException(ErrorStatus.NOT_FOUND_USER_EXCEPTION));


        return user.getId();
    }

    public String generateRefreshToken(final Long userId) {
        String jwtRefreshToken = jwtService.issueRefreshToken(String.valueOf(userId));

        RefreshToken refreshToken = new RefreshToken(jwtRefreshToken, userId);
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getRefreshToken();
    }

    public String generateAccessToken(final String refreshTokenId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenId)
                .orElseThrow(() -> new InvalidRefreshTokenException(ErrorStatus.INVALID_REFRESH_TOKEN_EXCEPTION));
        Long userId = refreshToken.getUserId();


        return jwtService.issueAccessToken(String.valueOf(userId));
    }
}
