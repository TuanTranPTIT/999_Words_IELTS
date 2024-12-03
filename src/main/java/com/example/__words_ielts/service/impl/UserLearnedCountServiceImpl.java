package com.example.__words_ielts.service.impl;

import com.example.__words_ielts.enums.ErrorCodeEnum;
import com.example.__words_ielts.exception.AppException;
import com.example.__words_ielts.model.dto.request.userLearnedCount.UpdateCountRequest;
import com.example.__words_ielts.model.dto.response.userLearnedCount.UserLearnedCountResponse;
import com.example.__words_ielts.model.entity.UserLearnedCount;
import com.example.__words_ielts.repository.UserLearnedCountRepository;
import com.example.__words_ielts.service.UserLearnedCountService;
import com.example.__words_ielts.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UserLearnedCountServiceImpl implements UserLearnedCountService {

    private final UserLearnedCountRepository userLearnedCountRepository;
    private final UserService userService;

    @Override
    public UserLearnedCountResponse getUserLearnedCountByTopicId(Integer topicId) {

        Integer userId = userService.getMyInfo().getId();

        UserLearnedCount userLearnedCount = userLearnedCountRepository.findByUserIdAndTopicId(userId,topicId);

        return UserLearnedCountResponse.fromUserLearnedCount(userLearnedCount);
    }

    @Override
    @Transactional
    public UserLearnedCountResponse updateCount(Integer topicId, UpdateCountRequest updateCountRequest) {
        Integer userId = userService.getMyInfo().getId();

        UserLearnedCount userLearnedCount = userLearnedCountRepository.findByUserIdAndTopicId(userId, topicId);

        // Kiểm tra và cập nhật từng trường
        if (updateCountRequest.getLearnedWordCount() != null
                && updateCountRequest.getLearnedWordCount() > userLearnedCount.getLearnedWordCount()) {
            userLearnedCount.setLearnedWordCount(updateCountRequest.getLearnedWordCount());
        }
        if (updateCountRequest.getGameImage() != null
                && updateCountRequest.getGameImage().getValue() > userLearnedCount.getGameImage().getValue()) {
            userLearnedCount.setGameImage(updateCountRequest.getGameImage());
        }
        if (updateCountRequest.getGameListen() != null
                && updateCountRequest.getGameListen().getValue() > userLearnedCount.getGameListen().getValue()) {
            userLearnedCount.setGameListen(updateCountRequest.getGameListen());
        }
        if (updateCountRequest.getGameSpeak() != null
                && updateCountRequest.getGameSpeak().getValue() > userLearnedCount.getGameSpeak().getValue()) {
            userLearnedCount.setGameSpeak(updateCountRequest.getGameSpeak());
        }
        if (updateCountRequest.getGameWord() != null
                && updateCountRequest.getGameWord().getValue() > userLearnedCount.getGameWord().getValue()) {
            userLearnedCount.setGameWord(updateCountRequest.getGameWord());
        }
        if (updateCountRequest.getGameListenAndWrite() != null
                && updateCountRequest.getGameListenAndWrite().getValue() > userLearnedCount.getGameListenAndWrite().getValue()) {
            userLearnedCount.setGameListenAndWrite(updateCountRequest.getGameListenAndWrite());
        }

        // Cập nhật timestamp
        userLearnedCount.setUpdatedAt(Timestamp.from(Instant.now()));

        // Lưu thay đổi vào database
        userLearnedCountRepository.save(userLearnedCount);

        // Trả về kết quả
        return UserLearnedCountResponse.fromUserLearnedCount(userLearnedCount);
    }
}
