package com.example.userdetails.service;

import java.io.File;
import java.io.IOException;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

@Service
public class TextExtractorService {
	
	public String extractTextFromPdf(MultipartFile file) throws Exception {
		if (file.isEmpty()) {
		    throw new RuntimeException("Uploaded file is empty.");
		}
		    try (PDDocument document = PDDocument.load(file.getInputStream())) {
	            PDFTextStripper pdfStripper = new PDFTextStripper();
	            String extractedText = pdfStripper.getText(document);
	            return extractedText;
	        } 
	        catch (IOException e) {
	            throw new IOException("Failed to extract text from PDF", e);
	        }
	    }
	}