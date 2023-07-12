package com.bluedragon.arth.user.domain.entity;

import com.bluedragon.arth.z_common.entity.BaseTimeEntity;
import com.bluedragon.arth.user.domain.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "tbl_user")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @Email
    private String email;

    @NotNull
    @JsonIgnore
    private String passWord;

    @NotNull
    @Column(unique = true)
    private String nickName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String name;

    private String major;

}