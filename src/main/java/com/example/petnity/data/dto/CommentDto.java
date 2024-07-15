package com.example.petnity.data.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class SaveCommentDto{
        private String userEmail;
        private Long postId;
        private String commentBody;

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class SaveSubDto{
        private String commentBody;
        private String userEmail;
        private Long postId;
        private Long commentId;
    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetCommentDto{
        private Long commentId;
        private String commentBody;
        private Long postId;
        private List<CommentDto.GetSubCommentDto> subCommentDtoList = new ArrayList<CommentDto.GetSubCommentDto>();
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static class GetSubCommentDto{
        private Long commentId;
        private String commentBody;
        private Long ownerCommentId;
        private LocalDateTime createdDate;
        private LocalDateTime lastModifiedDate;

    }

}
