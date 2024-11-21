package com.example.__words_ielts.repository;

import com.example.__words_ielts.model.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findAllByTopicId(Integer id);
}
