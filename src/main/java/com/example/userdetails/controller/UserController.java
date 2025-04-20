package com.example.userdetails.controller;

import com.example.userdetails.repository.*;
import com.example.userdetails.dto.LoginRequest;
import com.example.userdetails.model.User;
import com.example.userdetails.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") 
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private NewsController news;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
    	 System.out.println("Received user data: " + user);
        User registeredUser = userService.registerUser(user);
        return ResponseEntity.ok(registeredUser);
    }
    @PostMapping("/login")
    public ResponseEntity<String> loginuser(@RequestBody LoginRequest loginrequest)
    {
    	User user=userRepository.findByUsername(loginrequest.getUsername());
    	
    	if (user != null && user.getPassword().equals(loginrequest.getPassword()))
    	{
    		List<String> sports=user.getSports();
    		String spo=sports.stream().collect(Collectors.joining(","));
    		List<String> activities=user.getExtracurricularActivities();
    		String act=activities.stream().collect(Collectors.joining(","));
    		List<String> tech=user.getTechnology();
    		String tc=tech.stream().collect(Collectors.joining(","));
    		String intrest=spo+","+act+","+tc;
    		news.setintrest(intrest);
    		
    		
    		return ResponseEntity.ok("Login successful");
    	}
    	else
    		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect username or password");
    	
    }
    @GetMapping("/{userName}/academics")
    public ResponseEntity<User> getUserAcademics(@PathVariable String userName) {
        User user = userRepository.findByUsername(userName);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(user);
    }
}