package com.example.__words_ielts.service;

import com.example.__words_ielts.model.dto.response.word.WordResponse;
import com.example.__words_ielts.model.entity.Word;

import java.util.List;

public interface WordService {
    List<WordResponse> getWordByTopicId(Integer id);
    Word getById(Integer id);
}
