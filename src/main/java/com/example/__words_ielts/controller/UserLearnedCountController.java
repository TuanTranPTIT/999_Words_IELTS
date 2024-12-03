package com.example.__words_ielts.controller;

import com.example.__words_ielts.model.dto.request.userLearnedCount.UpdateCountRequest;
import com.example.__words_ielts.model.dto.response.user.ApiResponse;
import com.example.__words_ielts.model.dto.response.userLearnedCount.UserLearnedCountResponse;
import com.example.__words_ielts.service.UserLearnedCountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/userLearnedCount")
public class UserLearnedCountController {
    private final UserLearnedCountService userLearnedCountService;

    @GetMapping("/topic")
    ApiResponse<UserLearnedCountResponse> getUserLearnedCountByTopicId(@RequestParam("topicId") Integer topicId){
        return ApiResponse.<UserLearnedCountResponse>builder()
                .result(userLearnedCountService.getUserLearnedCountByTopicId(topicId))
                .build();
    }

    @PatchMapping("/topic")
    ApiResponse<UserLearnedCountResponse> updateCount(@RequestParam("topicId") Integer topicId, @RequestBody UpdateCountRequest updateCountRequest){
        return ApiResponse.<UserLearnedCountResponse>builder()
                .result(userLearnedCountService.updateCount(topicId, updateCountRequest))
                .build();
    }
}
