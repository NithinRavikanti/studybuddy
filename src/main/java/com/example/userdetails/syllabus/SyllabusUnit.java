package com.example.userdetails.syllabus;

import java.util.ArrayList;
import java.util.List;

public class SyllabusUnit {
    public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getTopics1() {
		return topics;
	}
	public void setTopics(List<String> topics) {
		this.topics = topics;
	}
	private String title;
    private List<String> topics = new ArrayList<>();
    
    // ... (existing getters and setters remain the same)
}

