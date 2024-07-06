package umc.study.converter;

import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ReviewConverter {
    public static ReviewResponseDTO.ReviewResultDTO toReviewResultDTO(Review review){
        return ReviewResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

//    public static Review toReview(ReviewRequestDTO.ReviewDto request){
//
//        return Review.builder()
//                .title(request.getTitle())
//                .score(request.getScore())
//                .body(request.getBody())
//                .reviewMemberList(new ArrayList<>())
//                .reviewStoreList(new ArrayList<>())
//                .build();
//    }

    public static ReviewImage toReviewImage(String pictureUrl, Review request){

        return ReviewImage.builder()
                .imageUrl(pictureUrl)
                .review(request)
                .build();
    }


}
