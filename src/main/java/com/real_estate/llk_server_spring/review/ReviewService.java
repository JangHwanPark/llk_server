package com.real_estate.llk_server_spring.review;

import com.real_estate.llk_server_spring.review.dto.AddReviewDTO;
import com.real_estate.llk_server_spring.review.entity.Review;
import com.real_estate.llk_server_spring.review.entity.ReviewRepository;
import com.real_estate.llk_server_spring.security.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final JWTUtil util;

    public ResponseEntity<?> addReviewProc(AddReviewDTO addReviewDTO) {
        Review review = new Review();
        review.setReviewName(addReviewDTO.getReviewName());
        review.setReviewDescription(addReviewDTO.getReviewDescription());
        review.setAddress(addReviewDTO.getAddress());
        Review result = reviewRepository.save(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    public ResponseEntity<?> getReviewList() {
        List<Review> reviewList = reviewRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(reviewList);
    }
}
