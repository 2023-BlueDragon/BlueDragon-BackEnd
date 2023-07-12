package com.bluedragon.arth.answer.application;

import com.bluedragon.arth.answer.domain.entity.Answer;
import com.bluedragon.arth.answer.domain.repository.AnswerJpaRepository;
import com.bluedragon.arth.answer.ui.dto.request.RegisterAnswerRequest;
import com.bluedragon.arth.answer.ui.dto.response.AnswerByQuestionResponse;
import com.bluedragon.arth.answer.ui.dto.response.AnswerResponse;
import com.bluedragon.arth.answer.ui.dto.response.MyAnswerResponse;
import com.bluedragon.arth.question.application.QuestionFacade;
import com.bluedragon.arth.question.domain.entity.Question;
import com.bluedragon.arth.user.application.UserFacade;
import com.bluedragon.arth.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final UserFacade userFacade;
    private final QuestionFacade questionFacade;
    private final AnswerJpaRepository answerJpaRepository;

    @Transactional(rollbackFor = Exception.class)
    public AnswerResponse register(RegisterAnswerRequest request) {
        User user = userFacade.getCurrentUser();
        Question question = questionFacade.getById(request.questionId());

        return AnswerResponse.toResponse(answerJpaRepository.save(Answer.builder()
                .answer(request.answer())
                .writer(user)
                .question(question)
                .fileUrlList(request.fileUrlList())
                .build()));
    }

    @Transactional(readOnly = true)
    public List<MyAnswerResponse> getMy() {
        return answerJpaRepository.findByWriter(userFacade.getCurrentUser()).stream()
                .map(MyAnswerResponse::toResponse).toList();
    }

    @Transactional(readOnly = true)
    public List<AnswerByQuestionResponse> getByQuestion(final long questionId) {
        return answerJpaRepository.findByQuestion(questionFacade.getById(questionId)).stream()
                .map(AnswerByQuestionResponse::toResponse).toList();
    }

}