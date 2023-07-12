package com.bluedragon.arth.answer.ui.dto.response;

import com.bluedragon.arth.answer.domain.entity.Answer;
import com.bluedragon.arth.user.domain.entity.User;

import java.time.LocalDateTime;

public record AnswerResponse(
        Long id,
        String answer,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        QuestionResponse question,
        User writer) {
    public static AnswerResponse toResponse(final Answer answer) {
        return new AnswerResponse(answer.getId(), answer.getAnswer(), answer.getCreatedAt(), answer.getModifiedAt(),
                QuestionResponse.toResponse(answer.getQuestion()), answer.getWriter());
    }
}