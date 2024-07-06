package umc.study.web.dto;

import lombok.Getter;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewDto{
        String title;
        Float score;
        String body;
        Long memberId;
        Long storeId;
    }

    @Getter
    public static class MyReviewDTO{
        Long memberId;

    }

}
