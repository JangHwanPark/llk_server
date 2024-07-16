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
import com.real_estate.llk_server_spring.util.LlkUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AgentRepository agentRepository;
    private final LlkUtil llkUtil;

    public ResponseEntity<?> addReviewProc(AddReviewDTO addReviewDTO, HttpServletRequest req) {
        Users user = llkUtil.usingRequestGetUser(req);
        Agent agent = llkUtil.usingLicenseNumberGetAgent(addReviewDTO.getAgentLicense());
        Review review = new Review();
        review.setReviewName(addReviewDTO.getReviewName());
        review.setReviewDescription(addReviewDTO.getReviewDescription());
        review.setAddress(addReviewDTO.getAddress());
        review.setReviewScore(BigDecimal.valueOf(addReviewDTO.getReviewScore()));
        review.setType(addReviewDTO.getReviewType());
        review.setUser(user);
        review.setAgent(agent);
        reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body("Success write review");
    }

    public ResponseEntity<?> getReviewListProc(String licenseNumber) {
        Map<String, Object> result = new HashMap<>();
        Agent agent = llkUtil.usingLicenseNumberGetAgent(licenseNumber);
        List<Review> reviews = reviewRepository.findByAgent(agent);
        List<Object> list = new ArrayList<>();
        for (Review review : reviews) {
            result.put("uesr_name",review.getUser().getEmail());
            result.put("review_name", review.getReviewName());
            result.put("review_description", review.getReviewDescription());
            result.put("review_address", review.getAddress());
            result.put("review_score", review.getReviewScore());
            result.put("review_type", review.getType());
            result.put("review_date", review.getCreated());
            result.put("review_edit_date", review.getUpdated());
            list.add(result);
        }
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
