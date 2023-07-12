package com.bluedragon.arth.answer.ui.dto.response;

import com.bluedragon.arth.answer.domain.entity.Answer;
import com.bluedragon.arth.user.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public record AnswerResponse(
        Long id,
        String answer,
        List<String> fileUrlList,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt,
        QuestionResponse question,
        User writer) {
    public static AnswerResponse toResponse(final Answer answer) {
        return new AnswerResponse(answer.getId(), answer.getAnswer(), answer.getFileUrlList(), answer.getCreatedAt(),
                answer.getModifiedAt(), QuestionResponse.toResponse(answer.getQuestion()), answer.getWriter());
    }
}