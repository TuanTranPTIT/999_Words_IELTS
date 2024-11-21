package com.example.__words_ielts.controller;

import com.example.__words_ielts.model.dto.response.user.ApiResponse;
import com.example.__words_ielts.model.entity.Topic;
import com.example.__words_ielts.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/topic")
public class TopicController {
    private final TopicService topicService;

    @GetMapping("/topics")
    public ApiResponse<List<Topic>> getAll(){
        return ApiResponse.<List<Topic>>builder()
                .result(topicService.findAll())
                .build();
    }

    @GetMapping
    public ApiResponse<Topic> getById(@RequestParam("id") Integer id){
        return ApiResponse.<Topic>builder()
                .result(topicService.findById(id))
                .build();
    }
}
