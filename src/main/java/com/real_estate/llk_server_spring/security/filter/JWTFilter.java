package com.real_estate.llk_server_spring.security.filter;

import com.real_estate.llk_server_spring.security.detail.CustomUserDetail;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import com.real_estate.llk_server_spring.user.entity.Users;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil util;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String access = request.getHeader("Access");

        if (access == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            util.isExpired(access);
        } catch (ExpiredJwtException e) {
            PrintWriter out = response.getWriter();
            out.print("Access Token Expired");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String category = util.getCategory(access);

        if(category.equals("Access")) {
            PrintWriter out = response.getWriter();
            out.print("Invalid Access Token");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String email = util.getEmail(access);
        String role = util.getRole(access);

        Users user = new Users(email, role);

        CustomUserDetail customUserDetail = new CustomUserDetail(user);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customUserDetail, null, customUserDetail.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }
}
