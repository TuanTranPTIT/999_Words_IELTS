package com.example.__words_ielts.service.impl;

import com.example.__words_ielts.enums.ErrorCodeEnum;
import com.example.__words_ielts.exception.AppException;
import com.example.__words_ielts.model.entity.Topic;
import com.example.__words_ielts.repository.TopicRepository;
import com.example.__words_ielts.service.TopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {
    private final TopicRepository topicRepository;

    @Override
    public List<Topic> findAll(){
        return topicRepository.findAll();
    }

    @Override
    public Topic findById(Integer id) {
        return topicRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCodeEnum.TOPIC_NOT_EXISTED));
    }
}
