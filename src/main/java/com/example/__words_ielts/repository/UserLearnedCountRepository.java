package com.example.__words_ielts.repository;

import com.example.__words_ielts.model.entity.UserLearnedCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLearnedCountRepository extends JpaRepository<UserLearnedCount, Integer> {

}
