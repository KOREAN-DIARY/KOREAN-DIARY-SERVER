package com.finalproject.kdiary.service;

import com.finalproject.kdiary.controller.user.dto.request.UserCreateRequestDto;
import com.finalproject.kdiary.controller.user.dto.response.UserCreateResponseDto;
import com.finalproject.kdiary.domain.User;
import com.finalproject.kdiary.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponseDto create(UserCreateRequestDto request) {
        User user = User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        userRepository.save(user);

        return UserCreateResponseDto.of(user.getId(), user.getEmail(), user.getName());

    }
}
