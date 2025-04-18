package com.example.userdetails.repository;

import com.example.userdetails.model.Topic;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    // You can add custom query methods here if needed
	List<Topic> findAllByDescriptionEquals(String description);
}