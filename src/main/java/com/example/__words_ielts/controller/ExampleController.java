package com.example.__words_ielts.controller;

import com.example.__words_ielts.model.dto.response.example.ExampleResponse;
import com.example.__words_ielts.model.dto.response.user.ApiResponse;
import com.example.__words_ielts.model.entity.Example;
import com.example.__words_ielts.service.ExampleService;
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
@RequestMapping("/example")
public class ExampleController {
    private final ExampleService exampleService;

    @GetMapping("/word")
    ApiResponse<List<ExampleResponse>> getExampleByWordId(@RequestParam("wordId") Integer wordId){
        return ApiResponse.<List<ExampleResponse>>builder()
                .result(exampleService.getExampleByWordId(wordId))
                .build();
    }
}
