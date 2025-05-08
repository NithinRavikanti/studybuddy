package com.example.userdetails.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.userdetails.dto.YoutubeVideoDto;

import com.example.userdetails.service.YoutubeService;

@RestController
@RequestMapping("/api/youtube")
@CrossOrigin(origins = "http://localhost:3000")  
public class YoutubeController {

	@Autowired
	private YoutubeService ytService;
	
	@GetMapping("/search")
	public List<YoutubeVideoDto> getTopVideo(@RequestParam String query)
	{
		System.out.println(query);
		return ytService.fetchTopVideo(query);
	}
}
