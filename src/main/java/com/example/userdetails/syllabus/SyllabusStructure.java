package com.example.userdetails.syllabus;

import java.util.ArrayList;
import java.util.List;

public class SyllabusStructure {
    private String syllabusTitle;  // Added syllabus title field
    private List<SyllabusUnit> units = new ArrayList<>();
    
    public String getSyllabusTitle() {
        return syllabusTitle;
    }
    
    public void setSyllabusTitle(String syllabusTitle) {
        this.syllabusTitle = syllabusTitle;
    }
    
    public void addUnit(SyllabusUnit unit) {
        units.add(unit);
    }
    
    public List<SyllabusUnit> getUnits() {
        return units;
    }
}

