package com.bluedragon.arth.answer.ui.dto.response;

import com.bluedragon.arth.answer.domain.entity.Answer;
import com.bluedragon.arth.question.domain.entity.Question;

import java.time.LocalDateTime;

public record MyAnswerResponse(
        Long id,
        String answer,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        QuestionResponse question) {
    public static MyAnswerResponse toResponse(final Answer answer) {
        return new MyAnswerResponse(answer.getId(), answer.getAnswer(),
                answer.getCreatedAt(), answer.getModifiedAt(), QuestionResponse.toResponse(answer.getQuestion()));
    }
}

record QuestionResponse(
        Long id,
        String title) {
    public static QuestionResponse toResponse(final Question question) {
        return new QuestionResponse(question.getId(), question.getTitle());
    }
}