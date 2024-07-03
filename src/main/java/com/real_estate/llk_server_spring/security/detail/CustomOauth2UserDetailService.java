package com.real_estate.llk_server_spring.security.detail;

import com.real_estate.llk_server_spring.user.dto.GoogleResponse;
import com.real_estate.llk_server_spring.user.dto.Oauth2Response;
import com.real_estate.llk_server_spring.user.dto.UserDTO;
import com.real_estate.llk_server_spring.user.entity.UserRepository;
import com.real_estate.llk_server_spring.user.entity.Users;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserDetailService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOauth2UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Oauth2Response oAuth2Response = null;

        if(registrationId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String username = oAuth2Response.getProvider()+" "+oAuth2Response.getProviderId();
        Users user = userRepository.findByUsername(username);
        if(user == null) {
            Users users = new Users();
            users.setUsername(username);
            users.setEmail(oAuth2Response.getEmail());
            users.setUserRole("ROLE_USER");
            userRepository.save(users);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");
            return new CustomOAuth2User(userDTO);
        } else {
            user.setEmail(oAuth2Response.getEmail());
            userRepository.save(user);

            UserDTO userDTO = new UserDTO();
            userDTO.setUsername(username);
            userDTO.setName(oAuth2Response.getName());
            userDTO.setRole("ROLE_USER");

            return new CustomOAuth2User(userDTO);
        }

    }
}
