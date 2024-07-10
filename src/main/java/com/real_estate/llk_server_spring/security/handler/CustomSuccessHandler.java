package com.real_estate.llk_server_spring.security.handler;

import com.real_estate.llk_server_spring.security.detail.CustomOAuth2User;
import com.real_estate.llk_server_spring.security.entity.Refresh;
import com.real_estate.llk_server_spring.security.entity.RefreshRepository;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Component
@RequiredArgsConstructor
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JWTUtil util;
    private final RefreshRepository refreshRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // OAuth2User
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String username = customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.iterator().next().getAuthority();

        // 토큰 생성
        String access = util.createJwt("Access", username, role, 6000L);
        String refresh = util.createJwt("Refresh", username, role, 60000L);

        // Refresh 토큰 저장
        addRefreshEntity(username, refresh, 60000L);

        // 응답 설정
        response.setHeader("Access", access);
        response.addCookie(createCookie("Refresh", refresh));
        response.setStatus(HttpStatus.OK.value());

        // 로그 추가
        System.out.println("Authentication successful. Redirecting to http://localhost:5173/");

        // sendRedirect 수정
        getRedirectStrategy().sendRedirect(request, response, "http://localhost:5173/");
    }

    private Cookie createCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(60 * 60 * 60);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        return cookie;
    }

    private void addRefreshEntity(String user, String refresh, Long expiredMs) {
        Date date = new Date(System.currentTimeMillis() + expiredMs);

        Refresh refreshEntity = new Refresh();
        refreshEntity.setUsername(user);
        refreshEntity.setRefresh(refresh);
        refreshEntity.setExpiration(date.toString());

        refreshRepository.save(refreshEntity);
    }
}