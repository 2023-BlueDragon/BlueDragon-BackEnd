package com.bluedragon.arth.question.application;

import com.bluedragon.arth.question.domain.entity.Question;
import com.bluedragon.arth.question.domain.repository.QuestionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionFacade {

    private final QuestionJpaRepository questionJpaRepository;

    public Question getById(final long questionId) {
        return questionJpaRepository.findById(questionId)
                .orElseThrow();
    }

}