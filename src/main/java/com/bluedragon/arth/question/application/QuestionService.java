package com.bluedragon.arth.question.application;

import com.bluedragon.arth.question.domain.entity.Question;
import com.bluedragon.arth.question.domain.repository.QuestionJpaRepository;
import com.bluedragon.arth.question.ui.dto.request.RegisterQuestionRequest;
import com.bluedragon.arth.question.ui.dto.response.MyQuestionResponse;
import com.bluedragon.arth.user.application.UserFacade;
import com.bluedragon.arth.z_common.dto.request.PageDataRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final UserFacade userFacade;
    private final QuestionJpaRepository questionJpaRepository;

    @Transactional(rollbackFor = Exception.class)
    public Question register(RegisterQuestionRequest request) {
        return questionJpaRepository.save(Question.builder()
                .title(request.title())
                .content(request.content())
                .writer(userFacade.getCurrentUser())
                .build());
    }

    @Transactional(readOnly = true)
    public List<MyQuestionResponse> getMy() {
        return questionJpaRepository.findByWriter(userFacade.getCurrentUser()).stream()
                .map(MyQuestionResponse::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public Page<Question> getAll(PageDataRequest pageDataRequest) {
        PageRequest pageRequest = PageRequest.of(pageDataRequest.page(), pageDataRequest.size(),
                Sort.by(Sort.Direction.DESC, "createdAt"));

        return questionJpaRepository.findAll(pageRequest);
    }

}