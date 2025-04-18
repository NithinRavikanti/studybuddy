package com.example.userdetails.model;

import java.util.List;

import jakarta.persistence.*;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String collegeName;

    @Column(nullable = false)
    private String year;

    @Column(nullable = false)
    private String branch;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Subject> syllabus; 

    public List<Subject> getSyllabus() {
		return syllabus;
	}
	public void setSyllabus(List<Subject> syllabus) {
		this.syllabus = syllabus;
	}
	
	@ElementCollection
    @CollectionTable(name = "sports", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "sport")
    private List<String> sports; // Add this field

    @ElementCollection
    @CollectionTable(name = "extracurricular_activities", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "activity")
    private List<String> extracurricularActivities;
    
    
    @ElementCollection
    @CollectionTable(name = "technology", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "technology")
    private List<String> technology;
    
    
	public List<String> getTechnology() {
		return technology;
	}
	public void setTechnology(List<String> technology) {
		this.technology = technology;
	}
	public List<String> getSports() {
		return sports;
	}
	public void setSports(List<String> sports) {
		this.sports = sports;
	}
	public List<String> getExtracurricularActivities() {
		return extracurricularActivities;
	}
	public void setExtracurricularActivities(List<String> extracurricularActivities) {
		this.extracurricularActivities = extracurricularActivities;
	}
	// Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getCollegeName() { return collegeName; }
    public void setCollegeName(String collegeName) { this.collegeName = collegeName; }

    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }

    public String getBranch() { return branch; }
    public void setBranch(String branch) { this.branch = branch; }
}