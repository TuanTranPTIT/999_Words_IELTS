package com.example.__words_ielts.controller;

import com.example.__words_ielts.model.dto.response.user.ApiResponse;
import com.example.__words_ielts.model.dto.response.word.WordResponse;
import com.example.__words_ielts.model.entity.Word;
import com.example.__words_ielts.service.WordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/word")
public class WordController {
    private final WordService wordService;

    @GetMapping("/topic")
    ApiResponse<List<WordResponse>> getWordByTopicId(@RequestParam("topicId") Integer id){
        return ApiResponse.<List<WordResponse>>builder()
                .result(wordService.getWordByTopicId(id))
                .build();
    }

    @GetMapping()
    ApiResponse<Word> getById(@RequestParam("id") Integer id){
        return ApiResponse.<Word>builder()
                .result(wordService.getById(id))
                .build();
    }
}
