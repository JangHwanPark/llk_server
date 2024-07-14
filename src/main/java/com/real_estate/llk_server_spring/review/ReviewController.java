package com.real_estate.llk_server_spring.review;

import com.real_estate.llk_server_spring.review.dto.AddReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/add")
    public ResponseEntity<?> addReview(@RequestBody AddReviewDTO addReviewDTO) {
        return reviewService.addReviewProc(addReviewDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getReviewList() {
        return reviewService.getReviewList();
    }
}
