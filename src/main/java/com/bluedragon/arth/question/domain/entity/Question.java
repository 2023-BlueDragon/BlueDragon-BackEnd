package com.bluedragon.arth.question.domain.entity;

import com.bluedragon.arth.z_common.entity.BaseTimeEntity;
import com.bluedragon.arth.user.domain.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tbl_question")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 55)
    private String title;

    @NotNull
    @Column(columnDefinition = "TEXT")
    private String content;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<String> fileUrlList;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private User writer;

}