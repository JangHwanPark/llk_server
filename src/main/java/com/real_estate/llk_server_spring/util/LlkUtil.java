package com.real_estate.llk_server_spring.util;

import com.real_estate.llk_server_spring.exception.LlkServerException;
import com.real_estate.llk_server_spring.exception.LlkServerExceptionErrorCode;
import com.real_estate.llk_server_spring.review.entity.Review;
import com.real_estate.llk_server_spring.review.entity.ReviewRepository;
import com.real_estate.llk_server_spring.review.entity.ReviewType;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import com.real_estate.llk_server_spring.user.entity.Agent;
import com.real_estate.llk_server_spring.user.entity.AgentRepository;
import com.real_estate.llk_server_spring.user.entity.UserRepository;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LlkUtil {
    private final JWTUtil util;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;
    private final ReviewRepository reviewRepository;

    public String usingRequestGetEmail(HttpServletRequest req) {
        String access = req.getHeader("Access");
        return util.getEmail(access);
    }

    public Users usingRequestGetUser(HttpServletRequest req) {
        String email = usingRequestGetEmail(req);
        return userRepository.findByEmail(email);
    }

    public Agent usingLicenseNumberGetAgent(String licenseNumber) {
        return agentRepository.findByLicenseNumber(licenseNumber)
                .orElseThrow(() -> new LlkServerException(HttpStatus.NOT_FOUND, LlkServerExceptionErrorCode.NOT_FOUNT_AGENT));
    }

    public List<Review> usingAgentEntityGetReview(Agent agent) {
        return reviewRepository.findByAgent(agent);
    }

    public void usingStringDataValidationCheck(String str) throws LlkServerException{
        if (str == null || str.isEmpty()) {
            throw new LlkServerException(HttpStatus.BAD_REQUEST,LlkServerExceptionErrorCode.DONT_REQUEST_DATA);
        }
    }
}
