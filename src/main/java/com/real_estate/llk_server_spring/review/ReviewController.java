package com.real_estate.llk_server_spring.review;

import com.real_estate.llk_server_spring.review.dto.AddReviewDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody AddReviewDTO addReviewDTO, HttpServletRequest req) {
        return reviewService.addReviewProc(addReviewDTO, req);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getReviewList(@RequestParam String licenseNumber) {
        return reviewService.getReviewList(licenseNumber);
    }
}
