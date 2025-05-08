package com.example.userdetails.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.userdetails.service.TextExtractorService;
import com.example.userdetails.syllabus.SyllabusParser;
import com.example.userdetails.syllabus.SyllabusStructure;

@RestController
@RequestMapping("/api/syllabus")
@CrossOrigin(origins = "http://localhost:3000")  
public class SyllabusController {
	
	@Autowired
	private TextExtractorService textextractor;
	
	@Autowired
	private SyllabusParser syllabusParser;
	
	@PostMapping(value = "/process-pdf", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<?> processSyllabusImage(@RequestParam("pdf") MultipartFile file) {
		
		try {
			String extractedText = textextractor.extractTextFromPdf(file);
			System.out.println(extractedText);
			
			 List<SyllabusStructure> structure = syllabusParser.parseSyllabusText(extractedText);
			 
			 return ResponseEntity.ok(structure);
		}
		catch (Exception e) {
			 return ResponseEntity.badRequest().body("Error processing image: " + e.getMessage());
		}
		
	}

}
