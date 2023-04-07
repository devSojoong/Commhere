package com.example.commhere.dto;

import com.example.commhere.entity.Review;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class ReviewDTO {

    private Long reviewId;
    private String content;
    private String nickname;
    private Integer rate;

    public ReviewDTO(Review review) {
        this.reviewId = review.getReviewId();
        this.content = review.getContent();
        this.nickname = review.getUser().getNickname();
        this.rate = review.getRate();
    }
}
