package com.example.__words_ielts.service.impl;

import com.example.__words_ielts.enums.ErrorCodeEnum;
import com.example.__words_ielts.exception.AppException;
import com.example.__words_ielts.model.dto.request.userLearnedWords.AddUserLearnedWordsRequest;
import com.example.__words_ielts.model.dto.response.userLearnedWords.UserLearnedWordsResponse;
import com.example.__words_ielts.model.entity.User;
import com.example.__words_ielts.model.entity.UserLearnedWords;
import com.example.__words_ielts.model.entity.Word;
import com.example.__words_ielts.repository.UserLearnedWordsRepository;
import com.example.__words_ielts.repository.UserRepository;
import com.example.__words_ielts.repository.WordRepository;
import com.example.__words_ielts.service.UserLearnedWordsService;
import com.example.__words_ielts.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserLearnedWordsServiceImpl implements UserLearnedWordsService {

    private final UserLearnedWordsRepository userLearnedWordsRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;

    @Override
    public List<UserLearnedWordsResponse> getUserLearnedWordsByTopicId(Integer topicId) {

        Integer userId = userService.getMyInfo().getId();

        List<UserLearnedWords> list = userLearnedWordsRepository.getUserLearnedWordByTopicId(topicId, userId);

        return list.stream()
                .map(UserLearnedWordsResponse::fromUserLearnedWords)
                .collect(Collectors.toList());
    }

    @Override
    public UserLearnedWordsResponse addUserLearnedWords(AddUserLearnedWordsRequest request) {

        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new AppException(ErrorCodeEnum.EMAIL_NOT_EXISTED));

        Word word = wordRepository.findById(request.getWordId())
                .orElseThrow(()-> new AppException(ErrorCodeEnum.WORD_NOT_EXISTED));

        // Kiểm tra xem UserLearnedWords đã tồn tại chưa
        Optional<UserLearnedWords> existingRecord = userLearnedWordsRepository.findByUserIdAndWordId(user.getId(), word.getId());
        if (existingRecord.isPresent()) {
            // Nếu đã tồn tại, trả về response từ bản ghi hiện tại
            return UserLearnedWordsResponse.fromUserLearnedWords(existingRecord.get());
        }

        UserLearnedWords newUserLearnedWords = UserLearnedWords.builder()
                .user(user)
                .word(word)
                .build();

        userLearnedWordsRepository.save(newUserLearnedWords);

        return UserLearnedWordsResponse.fromUserLearnedWords(newUserLearnedWords);
    }
}
