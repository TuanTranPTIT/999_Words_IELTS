package com.example.__words_ielts.controller;

import com.example.__words_ielts.model.dto.request.userLearnedWords.AddUserLearnedWordsRequest;
import com.example.__words_ielts.model.dto.response.user.ApiResponse;
import com.example.__words_ielts.model.dto.response.userLearnedWords.UserLearnedWordsResponse;
import com.example.__words_ielts.service.UserLearnedWordsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/userLearnedWords")
public class UserLearnedWordsController {
    private final UserLearnedWordsService userLearnedWordsService;

    @GetMapping("/topic")
    ApiResponse<List<UserLearnedWordsResponse>> getUserLearnedWordsByTopicId(@RequestParam("topicId") Integer topicId){
        return ApiResponse.<List<UserLearnedWordsResponse>>builder()
                .result(userLearnedWordsService.getUserLearnedWordsByTopicId(topicId))
                .build();
    }

    @PostMapping("/add")
    ApiResponse<UserLearnedWordsResponse> addUserLearnedWords(@RequestBody AddUserLearnedWordsRequest addUserLearnedWordsRequest){
        return ApiResponse.<UserLearnedWordsResponse>builder()
                .result(userLearnedWordsService.addUserLearnedWords(addUserLearnedWordsRequest))
                .build();
    }
}
