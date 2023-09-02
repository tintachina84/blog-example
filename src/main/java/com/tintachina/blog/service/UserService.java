package com.tintachina.blog.service;

import com.tintachina.blog.domain.User;
import com.tintachina.blog.dto.AddUserRequest;
import com.tintachina.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return this.userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(this.bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
}
