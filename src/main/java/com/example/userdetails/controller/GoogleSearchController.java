package com.example.userdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.example.userdetails.service.GoogleSearchService;

@RestController
@RequestMapping("/api/google-search")
@CrossOrigin(origins = "http://localhost:3000")  
public class GoogleSearchController {
	
	@Autowired
	GoogleSearchService googleSearchService;
	
	@GetMapping
	public List<Map<String, String>> search(@RequestParam String query) {
        return googleSearchService.serachWeb(query);
    }

}
