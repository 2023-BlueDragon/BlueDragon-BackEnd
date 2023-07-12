package com.bluedragon.arth.question.domain.repository;

import com.bluedragon.arth.question.domain.entity.Question;
import com.bluedragon.arth.user.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    @EntityGraph(attributePaths = {"writer"})
    List<Question> findByWriter(@NotNull User writer);

    @EntityGraph(attributePaths = {"writer"})
    Page<Question> findAll(Pageable pageable);

}