package com.example.__words_ielts.service.impl;

import com.example.__words_ielts.model.dto.response.example.ExampleResponse;
import com.example.__words_ielts.model.entity.Example;
import com.example.__words_ielts.repository.ExampleRepository;
import com.example.__words_ielts.service.ExampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExampleServiceImpl implements ExampleService {
    private final ExampleRepository exampleRepository;

    @Override
    public List<ExampleResponse> getExampleByWordId(Integer wordId) {
        List<Example> examples = exampleRepository.findAllByWordId(wordId);
        return examples.stream()
                .map(ExampleResponse::fromExample)
                .collect(Collectors.toList());
    }
}
