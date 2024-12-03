package com.example.__words_ielts.service;

import com.example.__words_ielts.model.dto.request.userLearnedCount.UpdateCountRequest;
import com.example.__words_ielts.model.dto.response.userLearnedCount.UserLearnedCountResponse;

import java.util.List;

public interface UserLearnedCountService {
    UserLearnedCountResponse getUserLearnedCountByTopicId(Integer topicId);
    UserLearnedCountResponse updateCount(Integer topicId, UpdateCountRequest updateCountRequest);
}
