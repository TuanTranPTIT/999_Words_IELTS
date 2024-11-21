package com.example.__words_ielts.repository;

import com.example.__words_ielts.model.entity.UserLearnedWords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLearnedWordsRepository extends JpaRepository<UserLearnedWords, Integer> {

}
