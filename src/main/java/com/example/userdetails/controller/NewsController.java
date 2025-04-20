package com.example.userdetails.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.userdetails.model.User;
import com.example.userdetails.repository.UserRepository;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "http://localhost:3000")  
public class NewsController {
	private final WebClient webClient;
	 private final String NEWS_API_KEY = "3c0807c004dc4981b037c5eb6a499df1";
	 private final String NEWS_API_URL = "https://newsapi.org/v2/everything";
	 @Autowired
	 private UserRepository userRepository;
	 public NewsController()
	 {
		 this.webClient = WebClient.create(NEWS_API_URL);
	 }
	String interests="india";
	 public void setintrest( String intrest)
	 {
		this.interests=intrest;
	 }
	 @GetMapping("/getnews")
	    public String getNewsByInterests()
	    {
		 String query = interests.replace(",", " OR ");
		 System.out.println("Fetching news for interest(s): " + interests);
		 return webClient.get()
				 .uri(uriBuilder -> uriBuilder
				 .queryParam("q", query)
                 .queryParam("apiKey", NEWS_API_KEY)
                 .build())
				 .retrieve()	
				 .bodyToMono(String.class).block(); 
	 }
	

}
