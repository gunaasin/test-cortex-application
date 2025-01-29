package com.clone.amazon.review;


import com.clone.amazon.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final JwtService jwtService;
    private final ReviewMapper reviewMapper;


    public Object addReview(ReviewRequestDTO reviewRequestDTO) {
        try {
            if (jwtService.validateToken(reviewRequestDTO.token(), reviewRequestDTO.email())) {
                Review review = reviewMapper.requestToReview(reviewRequestDTO);
                reviewRepository.save(review);
                return "updated";
            }
        } catch (Exception e) {
            System.out.println("exception in address service");
        }
        return "wrong credential";
    }
}
