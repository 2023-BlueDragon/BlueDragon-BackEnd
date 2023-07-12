package com.bluedragon.arth.question.domain.repository;

import com.bluedragon.arth.question.domain.entity.Question;
import com.bluedragon.arth.user.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionJpaRepository extends JpaRepository<Question, Long> {

    @EntityGraph(attributePaths = {"writer", "fileUrlList"})
    Optional<Question> findById(Long id);

    @EntityGraph(attributePaths = {"writer", "fileUrlList"})
    List<Question> findByWriter(@NotNull User writer);

    @EntityGraph(attributePaths = {"writer", "fileUrlList"})
    Page<Question> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"writer", "fileUrlList"})
    Page<Question> findByTitleContainingOrContentContaining(@NotNull @Size(min = 5, max = 55) String title, String content, Pageable pageable);

}