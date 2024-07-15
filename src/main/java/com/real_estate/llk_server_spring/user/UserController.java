package com.real_estate.llk_server_spring.user;

import com.real_estate.llk_server_spring.user.dto.EmailDTO;
import com.real_estate.llk_server_spring.user.dto.JoinDTO;
import com.real_estate.llk_server_spring.user.dto.RoleDTO;
import com.real_estate.llk_server_spring.user.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinDTO joinDTO) {
        return userService.userJoinProc(joinDTO);
    }

    @PostMapping("/availability/email")
    public ResponseEntity<?> emailAvailability(@RequestBody EmailDTO emailDTO) {
        return userService.emailAvailability(emailDTO);
    }

    @GetMapping("/admin/user/list")
    public ResponseEntity<?> getUserList() {
        return userService.getUserListProc();
    }

    @PostMapping("/admin/user/authorization")
    public ResponseEntity<?> agentAuthorization(@RequestBody RoleDTO roleDTO) {
        return userService.agentAuthorizationProc(roleDTO);
    }
}
