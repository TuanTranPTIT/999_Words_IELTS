package com.example.__words_ielts.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "word")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String type;

    @Column(nullable = false, length = 255)
    private String pronunciation;

    @Column(nullable = false)
    private String audio;

    @Column(nullable = false)
    private String definition;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false, foreignKey = @ForeignKey(name = "fk_word_topic_id"))
    private Topic topic;

    @Column(name = "create_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Timestamp updateAt;
}
