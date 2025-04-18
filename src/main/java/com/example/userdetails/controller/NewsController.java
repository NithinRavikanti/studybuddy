package com.example.userdetails.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/news")
public class NewsController {
	private final WebClient webClient;
	 private final String NEWS_API_KEY = "3c0807c004dc4981b037c5eb6a499df1";
	 private final String NEWS_API_URL = "https://newsapi.org/v2/everything";
	 public NewsController()
	 {
		 this.webClient = WebClient.create(NEWS_API_URL);
	 }
	 @GetMapping
	    public Mono<String> getNewsByInterests(
	            @RequestParam String interests 
	    ) 
	    {
		 String query = interests.replace(",", " OR ");
		 return webClient.get()
				 .uri(uriBuilder -> uriBuilder
				 .queryParam("q", query)
                 .queryParam("apiKey", NEWS_API_KEY)
                 .build())
				 .retrieve()	
				 .bodyToMono(String.class); 
	 }
	

}
