package com.example.__words_ielts.repository;

import com.example.__words_ielts.model.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Integer> {
}
