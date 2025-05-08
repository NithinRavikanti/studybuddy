package com.example.userdetails.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.userdetails.model.Topic;
import com.example.userdetails.repository.TopicRepository;
@Service
public class TopicService {

	
	 @Autowired
	    private TopicRepository topicRepository;

	    @Autowired
	    private RestTemplate restTemplate;
	    
	    private boolean isRunning = false;
	    
	    
	    public List<Topic> getTopicsWithEmptyDescription() {
	        return topicRepository.findAllByDescriptionEquals(null);
	    }
	    
	    @EventListener(ApplicationReadyEvent.class)
	    public void onApplicationStart() {
	        startDescriptionUpdateProcess();
	    }
	    
	    public void startDescriptionUpdateProcess() {
	    	if (isRunning) {
	    		return;
	    	}
	    	isRunning=true;
	    	
	    	new Thread(() -> {
	    		while (isRunning) {
	    			 List<Topic> topics = getTopicsWithEmptyDescription();
	    			 System.out.println(topics);
	    			 if (topics.isEmpty()) {
	                     isRunning = false; // Stop if no topics are left
	                     break;
	                 }
	    			 for (Topic topic : topics) {
	    	    		 String description = fetchDescriptionFromOllemaChat(topic.getName());
	    	    		 topic.setDescription(description);
	    	    		 topicRepository.save(topic);
	    	    	}
	    			 try {
	                     Thread.sleep(5000); // Add a delay between iterations (e.g., 5 seconds)
	                 } catch (InterruptedException e) {
	                     Thread.currentThread().interrupt();
	                     isRunning = false;
	                     break;
	                 }
	             }
	         }).start();
	    		}
	    
	    private static final String OLLEMA_CHAT_API_URL = "http://localhost:8081/ai/prompt";
	    private String fetchDescriptionFromOllemaChat(String topicName)
	    {
	    	System.out.println(topicName);
	    	 String url = OLLEMA_CHAT_API_URL + "?prompt=" + topicName;
	    	 return restTemplate.getForObject(url, String.class);
	    }
}
