package dev.ghazi.services;

import java.util.List;

import com.vaadin.hilla.Endpoint;

import dev.ghazi.dto.UserRecord;
import dev.ghazi.mapper.UserMapper;
import dev.ghazi.repository.UserRepository;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;

@Endpoint
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @PermitAll
    public List<UserRecord> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserMapper.toUserRecord(user))
                .toList();
    }
}
