package com.bluedragon.arth.answer.ui.controller;

import com.bluedragon.arth.answer.application.AnswerService;
import com.bluedragon.arth.answer.ui.dto.request.RegisterAnswerRequest;
import com.bluedragon.arth.answer.ui.dto.response.AnswerByQuestionResponse;
import com.bluedragon.arth.answer.ui.dto.response.AnswerResponse;
import com.bluedragon.arth.answer.ui.dto.response.MyAnswerResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Answer API")
@RestController
@RequestMapping(value = "/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @Operation(description = "Answer 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public AnswerResponse register(@RequestBody RegisterAnswerRequest request) {
        return answerService.register(request);
    }

    @Operation(description = "내 Answer List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public List<MyAnswerResponse> getMy() {
        return answerService.getMy();
    }

    @Operation(description = "Question에 해당하는 Answer List 조회")
    @GetMapping("/list/question/{questionId}")
    public List<AnswerByQuestionResponse> getByQuestion(final @PathVariable long questionId) {
        return answerService.getByQuestion(questionId);
    }

}