package com.example.__words_ielts.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "topic")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false)
    private String image;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updateAt;
}
