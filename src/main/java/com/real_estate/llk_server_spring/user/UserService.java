package com.real_estate.llk_server_spring.user;

import com.real_estate.llk_server_spring.user.dto.EmailDTO;
import com.real_estate.llk_server_spring.user.dto.JoinDTO;
import com.real_estate.llk_server_spring.user.entity.UserRoles;
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
        System.out.println("JoinDTO: " + joinDTO.toString());
        if(joinDTO.getEmail().isEmpty() && joinDTO.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
        if(userRepository.existsByEmail(joinDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body("Username already exists");
        }
        Users user = new Users();
        user.setEmail(joinDTO.getEmail());
        user.setUserPw(passwordEncoder.encode(joinDTO.getPassword()));
        user.setUserPhone(joinDTO.getPhone());
        user.setUserRoles(UserRoles.ROLE_USER);
        System.out.println("User: " + user.toString());
        userRepository.save(user);
        return ResponseEntity.ok().body("User successfully joined");
    }

    public ResponseEntity<?> emailAvailability(EmailDTO emailDTO) {
        if(emailDTO.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email");
        }
        if(!userRepository.existsByEmail(emailDTO.getEmail())) {
            return ResponseEntity.ok().body(false);
        }
        return ResponseEntity.ok().body(true);
    }
}