package com.bluedragon.arth.question.ui.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record RegisterQuestionRequest(
        @NotBlank
        String title,
        @NotBlank
        String content,
        List<String> fileUrlList) {}