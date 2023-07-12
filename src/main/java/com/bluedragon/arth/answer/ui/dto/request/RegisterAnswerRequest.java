package com.bluedragon.arth.answer.ui.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RegisterAnswerRequest(
        @NotBlank
        String answer,
        @NotNull
        Long questionId,
        List<String> fileUrlList) {}