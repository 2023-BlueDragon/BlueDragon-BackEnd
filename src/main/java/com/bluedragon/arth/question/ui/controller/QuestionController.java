package com.bluedragon.arth.question.ui.controller;

import com.bluedragon.arth.question.application.QuestionService;
import com.bluedragon.arth.question.domain.entity.Question;
import com.bluedragon.arth.question.ui.dto.request.RegisterQuestionRequest;
import com.bluedragon.arth.question.ui.dto.response.MyQuestionResponse;
import com.bluedragon.arth.z_common.dto.request.PageDataRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@Tag(name = "Question API")
@RestController
@RequestMapping(value = "/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @Operation(description = "Question 등록")
    @PostMapping
    @ResponseStatus(CREATED)
    public Question register(@RequestBody RegisterQuestionRequest request) {
        return questionService.register(request);
    }

    @Operation(description = "내 Question List 조회")
    @GetMapping("/my")
    @ResponseStatus(OK)
    public List<MyQuestionResponse> getMy() {
        return questionService.getMy();
    }

    @Operation(description = "모든 Question List 조회(Paging)")
    @GetMapping("/all")
    @ResponseStatus(OK)
    public Page<Question> getAll(@ModelAttribute PageDataRequest pageRequest) {
        return questionService.getAll(pageRequest);
    }

}