package com.bluedragon.arth.question.ui.dto.response;

import com.bluedragon.arth.question.domain.entity.Question;

import java.time.LocalDateTime;
import java.util.List;

public record MyQuestionResponse(
        long id,
        String title,
        String content,
        List<String> fileUrlList,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static MyQuestionResponse toResponse(final Question question) {
        return new MyQuestionResponse(question.getId(), question.getTitle(), question.getContent(),
                question.getFileUrlList(), question.getCreatedAt(), question.getModifiedAt());
    }
}