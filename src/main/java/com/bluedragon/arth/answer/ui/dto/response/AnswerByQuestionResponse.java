package com.bluedragon.arth.answer.ui.dto.response;

import com.bluedragon.arth.answer.domain.entity.Answer;
import com.bluedragon.arth.user.domain.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public record AnswerByQuestionResponse(
        Long id,
        String answer,
        List<String> fileUrlList,
        WriterResponse writer,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt) {
    public static AnswerByQuestionResponse toResponse(final Answer answer) {
        return new AnswerByQuestionResponse(answer.getId(), answer.getAnswer(), answer.getFileUrlList(),
                WriterResponse.toResponse(answer.getWriter()), answer.getCreatedAt(), answer.getModifiedAt());
    }
}

record WriterResponse(
        String email,
        String name,
        String nickName) {
    public static WriterResponse toResponse(final User writer) {
        return new WriterResponse(writer.getEmail(), writer.getName(), writer.getNickName());
    }
}