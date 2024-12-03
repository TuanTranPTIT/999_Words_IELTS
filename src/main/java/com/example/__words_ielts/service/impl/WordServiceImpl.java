package com.example.__words_ielts.service.impl;

import com.example.__words_ielts.enums.ErrorCodeEnum;
import com.example.__words_ielts.exception.AppException;
import com.example.__words_ielts.model.dto.response.word.WordResponse;
import com.example.__words_ielts.model.entity.Word;
import com.example.__words_ielts.repository.WordRepository;
import com.example.__words_ielts.service.WordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
    private final WordRepository wordRepository;


    @Override
    public List<WordResponse> getWordByTopicId(Integer topicId) {
        List<Word> words = wordRepository.findAllByTopicId(topicId);
        return words.stream()
                .map(WordResponse::fromWord)
                .collect(Collectors.toList());
    }

    @Override
    public Word getById(Integer id) {
        return wordRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCodeEnum.WORD_NOT_EXISTED));
    }
}
