package com.example.userdetails.syllabus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class SyllabusParser {

    private static final Pattern COURSE_PATTERN = Pattern.compile("(A\\d{5})\\s+(.*?)$");
    private static final Pattern UNIT_PATTERN = Pattern.compile("(?i)(Unit[-\\s]*[IVXLCDM]+)[:\\s]*(.*)");

    public List<SyllabusStructure> parseSyllabusText(String rawText) {
        List<SyllabusStructure> syllabuses = new ArrayList<>();

        String[] lines = rawText.split("\\r?\\n");

        SyllabusStructure currentSyllabus = null;
        SyllabusUnit currentUnit = null;
        boolean startParsing = false;

        for (String line : lines) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Step 1: Start parsing only when real subject begins
            if (!startParsing && line.matches(".*Microwave and Radar Engineering.*")) {
                startParsing = true;
            }
            if (!startParsing) continue;

            // Step 2: Match Course
            Matcher courseMatcher = COURSE_PATTERN.matcher(line);
            Matcher unitMatcher = UNIT_PATTERN.matcher(line);

            if (courseMatcher.find()) {
                // New Course
                if (currentSyllabus != null) {
                    syllabuses.add(currentSyllabus);
                }
                currentSyllabus = new SyllabusStructure();
                currentSyllabus.setSyllabusTitle(courseMatcher.group(2).trim());
                currentUnit = null; // reset unit
            } 
            else if (unitMatcher.find()) {
                // New Unit
                if (currentSyllabus != null) {
                    currentUnit = new SyllabusUnit();
                    currentUnit.setTitle(unitMatcher.group(2).trim());
                    currentSyllabus.addUnit(currentUnit);
                }
            } 
            else {
                // Normal topic text
                if (currentUnit != null) {
                	List<String> splitTopics = smartSplitTopics(line);
                    currentUnit.getTopics1().addAll(splitTopics);
                }
            }
        }

        // Final add
        if (currentSyllabus != null) {
            syllabuses.add(currentSyllabus);
        }

        return syllabuses;
    }
    private List<String> smartSplitTopics(String line) {
        List<String> topics = new ArrayList<>();
        
        // Split based on special characters: -, ; , :
        String[] parts = line.split("[-,;:]+");

        for (String part : parts) {
            String trimmed = part.trim();
            if (!trimmed.isEmpty()) {
                topics.add(trimmed);
            }
        }
        
        return topics;
    }

}
