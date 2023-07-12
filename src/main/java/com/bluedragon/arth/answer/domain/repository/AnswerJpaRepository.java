package com.bluedragon.arth.answer.domain.repository;

import com.bluedragon.arth.answer.domain.entity.Answer;
import com.bluedragon.arth.user.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerJpaRepository extends JpaRepository<Answer, Long> {

    @EntityGraph(attributePaths = {"question"})
    List<Answer> findByWriter(@NotNull User writer);

}