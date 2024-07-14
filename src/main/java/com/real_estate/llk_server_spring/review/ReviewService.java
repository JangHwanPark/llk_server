package com.real_estate.llk_server_spring.review;

import com.real_estate.llk_server_spring.exception.LlkServerException;
import com.real_estate.llk_server_spring.exception.LlkServerExceptionErrorCode;
import com.real_estate.llk_server_spring.review.dto.AddReviewDTO;
import com.real_estate.llk_server_spring.review.entity.Review;
import com.real_estate.llk_server_spring.review.entity.ReviewRepository;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import com.real_estate.llk_server_spring.user.entity.Agent;
import com.real_estate.llk_server_spring.user.entity.AgentRepository;
import com.real_estate.llk_server_spring.user.entity.UserRepository;
import com.real_estate.llk_server_spring.user.entity.Users;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final JWTUtil util;
    private final UserRepository userRepository;
    private final AgentRepository agentRepository;

    public ResponseEntity<?> addReviewProc(AddReviewDTO addReviewDTO, HttpServletRequest req) {
        String access = req.getHeader("Access");
        String email = util.getEmail(access);
        Users user = userRepository.findByEmail(email);
        Agent agent = agentRepository.findByLicenseNumber(addReviewDTO.getAgentLicense())
                .orElseThrow( ()-> new LlkServerException(HttpStatus.NOT_FOUND, LlkServerExceptionErrorCode.NOT_FOUNT_AGENT));
        Review review = new Review();
        review.setReviewName(addReviewDTO.getReviewName());
        review.setReviewDescription(addReviewDTO.getReviewDescription());
        review.setAddress(addReviewDTO.getAddress());
        review.setUser(user);
        review.setAgent(agent);
        Review result = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    public ResponseEntity<?> getReviewList() {
        List<Review> reviewList = reviewRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(reviewList);
    }
}
