package com.bluedragon.arth.question.ui.dto.response;

import com.bluedragon.arth.question.domain.entity.Question;

import java.time.LocalDateTime;

public record MyQuestionResponse(
        long id,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static MyQuestionResponse toResponse(final Question question) {
        return new MyQuestionResponse(question.getId(), question.getTitle(), question.getContent(),
                question.getCreatedAt(), question.getModifiedAt());
    }
}