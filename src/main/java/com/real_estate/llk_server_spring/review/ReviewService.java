package com.real_estate.llk_server_spring.review;

import com.real_estate.llk_server_spring.exception.LlkServerException;
import com.real_estate.llk_server_spring.exception.LlkServerExceptionErrorCode;
import com.real_estate.llk_server_spring.review.dto.AddReviewDTO;
import com.real_estate.llk_server_spring.review.entity.Review;
import com.real_estate.llk_server_spring.review.entity.ReviewRepository;
import com.real_estate.llk_server_spring.review.entity.ReviewType;
import com.real_estate.llk_server_spring.user.entity.Agent;
import com.real_estate.llk_server_spring.user.entity.AgentRepository;
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
        try {
            llkUtil.usingStringDataValidationCheck(addReviewDTO.getReviewName());
            llkUtil.usingStringDataValidationCheck(addReviewDTO.getReviewDescription());
            llkUtil.usingStringDataValidationCheck(addReviewDTO.getAddress());
            llkUtil.usingStringDataValidationCheck(addReviewDTO.getAgentLicense());
            llkUtil.usingStringDataValidationCheck(String.valueOf(addReviewDTO.getReviewType()));
            llkUtil.usingStringDataValidationCheck(String.valueOf(addReviewDTO.getReviewScore()));
        }catch (LlkServerException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
        }
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
        Agent agent = llkUtil.usingLicenseNumberGetAgent(licenseNumber);
        List<Review> reviews = reviewRepository.findByAgent(agent);
        List<Map<String, Object>> list = new ArrayList<>();

        for (Review review : reviews) {
            Map<String, Object> result = new HashMap<>();
            result.put("review_id", review.getId());
            result.put("user_name", review.getUser().getEmail());
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