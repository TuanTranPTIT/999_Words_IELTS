package com.example.__words_ielts.service;

import com.example.__words_ielts.model.entity.Topic;

import java.util.List;

public interface TopicService {
    List<Topic> findAll();
    Topic findById(Integer id);
}
