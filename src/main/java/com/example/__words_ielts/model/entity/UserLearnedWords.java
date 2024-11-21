package com.example.__words_ielts.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "user_learned_words")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLearnedWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_learned_words_user_id"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user_learned_words_word_id"))
    private Word word;

    @Column(name = "learned_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp learnedAt;
}
