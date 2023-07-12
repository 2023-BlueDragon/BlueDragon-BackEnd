package com.bluedragon.arth.answer.domain.entity;

import com.bluedragon.arth.question.domain.entity.Question;
import com.bluedragon.arth.z_common.entity.BaseTimeEntity;
import com.bluedragon.arth.user.domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tbl_answer")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String answer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

}