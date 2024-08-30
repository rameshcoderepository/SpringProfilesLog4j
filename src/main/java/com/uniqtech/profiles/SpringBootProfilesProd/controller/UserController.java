package com.uniqtech.profiles.SpringBootProfilesProd.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uniqtech.profiles.SpringBootProfilesProd.bean.User;
import com.uniqtech.profiles.SpringBootProfilesProd.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/test")
	public String testEndpoint() {
		logger.info("Info log in UserController.");
		logger.debug("Debug log in UserController.");
		logger.error("Error log in UserController.");

		return "Check the logs for various log levels!";
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		logger.info("Info log in UserController:: Inside getAllUsers() Method");
		logger.debug("Debug log in UserController:: Inside getAllUsers() Method");
		logger.error("Error log in UserController:: Inside getAllUsers() Method");
		return userService.getAllUsers();
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {

		logger.info("Info log in UserController:: Inside getUserById() Method");
		return userService.getUserById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		logger.info("Info log in UserController:: Inside createUser() Method");
		return userService.createUser(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		try {
			logger.info("Info log in UserController:: Inside updateUser() Method");
			return ResponseEntity.ok(userService.updateUser(id, userDetails));
		} catch (RuntimeException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		logger.info("Info log in UserController:: Inside deleteUser() Method");
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/by-username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
		User user = userService.findByUsername(username);
		logger.info("Info log in UserController:: Inside getUserByUsername() Method");
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@GetMapping("/by-email/{email}")
	public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
		User user = userService.findByEmail(email);
		logger.info("Info log in UserController:: Inside getUserByEmail() Method");
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}
}
