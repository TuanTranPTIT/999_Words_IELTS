package com.example.__words_ielts.model.entity;

import com.example.__words_ielts.enums.UserRoleEnum;
import com.example.__words_ielts.enums.UserStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "user")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(name = "hash_password", nullable = false, length = 100)
    private String hashPassword;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, length = 50)
    private UserRoleEnum role = UserRoleEnum.USER;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updateAt;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private UserStatusEnum status = UserStatusEnum.ACTIVE;
}