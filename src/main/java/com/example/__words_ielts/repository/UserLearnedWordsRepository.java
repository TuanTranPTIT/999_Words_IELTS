package com.example.__words_ielts.repository;

import com.example.__words_ielts.model.entity.UserLearnedWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLearnedWordsRepository extends JpaRepository<UserLearnedWords, Integer> {

    @Query(value = "SELECT ulw.* " +
            "FROM user_learned_words ulw " +
            "JOIN word w ON ulw.word_id = w.id " +
            "WHERE w.topic_id = :topicId AND ulw.user_id = :userId",
            nativeQuery = true)
    List<UserLearnedWords> getUserLearnedWordByTopicId(@Param("topicId") Integer topicId, @Param("userId") Integer userId);

    Optional<UserLearnedWords> findByUserIdAndWordId(Integer userId, Integer wordId);
}
