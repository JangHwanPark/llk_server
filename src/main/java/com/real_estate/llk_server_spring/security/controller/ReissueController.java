package com.real_estate.llk_server_spring.security.controller;

import com.real_estate.llk_server_spring.security.entity.Refresh;
import com.real_estate.llk_server_spring.security.entity.RefreshRepository;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class ReissueController {
    private final JWTUtil util;
    private final RefreshRepository refreshRepository;

    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(HttpServletRequest req, HttpServletResponse res) {
        String refresh = null;

        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("Refresh")) {
                refresh = cookie.getValue();
            }
        }

        if (refresh == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is null");
        }

        try {
            util.isExpired(refresh);
        } catch (ExpiredJwtException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is expired");
        }

        String category = util.getCategory(refresh);
        if(!category.equals("Refresh")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is invalid");
        }

        Boolean isExist = refreshRepository.existsByRefresh(refresh);
        if(!isExist) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Refresh token is invalid");
        }

        String username = util.getEmail(refresh);
        String role = util.getRole(refresh);

        String newAccess = util.createJwt("Access",username,role,10*10000L);
        String newRefresh = util.createJwt("Refresh",username,role,30*10000L);

        refreshRepository.deleteByRefresh(refresh);
        addRefreshEntity(username,newRefresh,60000L);

        res.addHeader("Access", newAccess);
        res.addCookie(createCookie("Refresh",newRefresh));

        return ResponseEntity.ok().body("Refresh successful");
    }

    private Cookie createCookie(String key, String value) {

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setHttpOnly(true);

        return cookie;
    }

    private void addRefreshEntity(String username, String refresh, Long expiredMs) {

        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = new Refresh();
        refreshEntity.setUsername(username);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}
