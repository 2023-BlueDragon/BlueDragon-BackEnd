package com.bluedragon.arth.answer.ui.dto.request;

public record RegisterAnswerRequest(
        String answer,
        Long questionId) {}