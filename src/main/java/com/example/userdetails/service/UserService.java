package com.example.userdetails.service;

import com.example.userdetails.model.Subject;
import com.example.userdetails.model.Topic;
import com.example.userdetails.model.Unit;
import com.example.userdetails.model.User;
import com.example.userdetails.repository.TopicRepository;
import com.example.userdetails.repository.UnitRepository;
import com.example.userdetails.repository.UserRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UnitRepository unitRepository;
    @Autowired
    private TopicService tp;

    public void createTopicsForUnit(Long unitId, List<Topic> topics) {
        Unit unit = unitRepository.findById(unitId)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        for (Topic topic : topics) {
            topic.setUnit(unit); // Set the unit here!
            topicRepository.save(topic);
        }
    }

    public User registerUser(User user) {

        for (Subject subject : user.getSyllabus()) {
            for (Unit unit : subject.getUnits()) {
                for (Topic topic : unit.getTopics()) {
                    topic.setUnit(unit);
                }
            }
        }
        tp.startDescriptionUpdateProcess();
        return userRepository.save(user); 
    }
}