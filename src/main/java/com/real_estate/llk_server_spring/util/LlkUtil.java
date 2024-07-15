package com.real_estate.llk_server_spring.util;

import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import com.real_estate.llk_server_spring.user.entity.UserRepository;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LlkUtil {
    private final JWTUtil util;
    private final UserRepository userRepository;

    public String usingRequestGetEmail(HttpServletRequest req) {
        String access = req.getHeader("Access");
        return util.getEmail(access);
    }

    public Users usingRequestGetUser(HttpServletRequest req) {
        String email = usingRequestGetEmail(req);
        return userRepository.findByEmail(email);
    }
}
