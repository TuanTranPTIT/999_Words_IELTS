package com.example.__words_ielts.service;

import com.example.__words_ielts.model.dto.response.example.ExampleResponse;
import com.example.__words_ielts.model.entity.Example;

import java.util.List;

public interface ExampleService {
    List<ExampleResponse> getExampleByWordId(Integer wordId);
}
