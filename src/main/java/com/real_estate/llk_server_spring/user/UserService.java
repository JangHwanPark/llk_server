package com.real_estate.llk_server_spring.user;

import com.real_estate.llk_server_spring.user.dto.JoinDTO;
import com.real_estate.llk_server_spring.user.entity.Users;
import com.real_estate.llk_server_spring.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ResponseEntity<?> userJoinProc(JoinDTO joinDTO) {
        if(joinDTO.getPassword().isEmpty() && joinDTO.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        if(userRepository.existsByUsername(joinDTO.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT.value()).body("Username already exists");
        }
        Users user = new Users();
        user.setUsername(joinDTO.getUsername());
        user.setPassword(passwordEncoder.encode(joinDTO.getPassword()));
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return ResponseEntity.ok().body("User successfully joined");
    }
}