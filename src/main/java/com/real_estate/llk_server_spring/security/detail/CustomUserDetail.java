package com.real_estate.llk_server_spring.security.detail;

import com.real_estate.llk_server_spring.user.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetail implements UserDetails {
    private final Users users;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return users.getUserRole();
            }
        });
        return authorities;
    }

    @Override
    public String getPassword() {
        return users.getUserPw();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }
    // 아래의 매소드 들은 사용자의 아이디가 만료, 잠김, 사용가능 한지에 대한 여부를 반환하는 메서드 입니다.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
