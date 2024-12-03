package com.example.__words_ielts.service;

import com.example.__words_ielts.model.dto.request.userLearnedWords.AddUserLearnedWordsRequest;
import com.example.__words_ielts.model.dto.response.userLearnedWords.UserLearnedWordsResponse;
import com.example.__words_ielts.model.entity.UserLearnedWords;

import java.util.List;

public interface UserLearnedWordsService {
    List<UserLearnedWordsResponse> getUserLearnedWordsByTopicId(Integer topicId);
    UserLearnedWordsResponse addUserLearnedWords(AddUserLearnedWordsRequest request);
}
