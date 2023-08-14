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
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

@Service
public class UserService {

    private static final String SECRET_KEY = "abcdefgabcdefgabcdefgabcdefgabcdefgabcdefg";
    private static final int ACCESS_TOKEN_EXPIRES = 30000;
    private final SecretKey secretKey;

    @Value("${oauth2.google.client-id}")
    private String googleClientKey;

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


    private Payload verifyToken(String idTokenString) throws GeneralSecurityException, IOException {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientKey))
                .build();


        GoogleIdToken idToken = verifier.verify(idTokenString);
        if (idToken != null) {
            return idToken.getPayload();
        } else {
            throw new InvalidGoogleTokenException(ErrorStatus.INVALID_GOOGLE_TOKEN_EXCEPTION);
        }
    }

    @Transactional
    public User create(UserCreateRequestDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public String login(UserLoginRequestDto request) {
        Optional<User> user = userRepository.findByEmail(request.getEmail());
        String userId;
        if (user.isEmpty()) {
            UserCreateRequestDto newUserRequest = new UserCreateRequestDto(request.getEmail(), request.getName());
            userId = this.create(newUserRequest).getId();
        } else {
            userId = user.get().getId();
        }

        return userId;
    }


    public String generateRefreshToken(final String userId) {
        String jwtRefreshToken = jwtService.issueRefreshToken(String.valueOf(userId));

        RefreshToken refreshToken = new RefreshToken(jwtRefreshToken, userId);
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getRefreshToken();
    }

    public String generateAccessToken(final String refreshTokenId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(refreshTokenId)
                .orElseThrow(() -> new InvalidRefreshTokenException(ErrorStatus.INVALID_REFRESH_TOKEN_EXCEPTION));
        String userId = refreshToken.getUserId();


        return jwtService.issueAccessToken(String.valueOf(userId));
    }
}
