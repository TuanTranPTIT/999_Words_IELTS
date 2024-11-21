package com.example.__words_ielts.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "example")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Example {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String audio;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false, foreignKey = @ForeignKey(name = "fk_example_word_id"))
    private Word word;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updateAt;
}