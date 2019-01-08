package com.pvthach.calculator.controller;

import com.pvthach.calculator.model.User;
import com.pvthach.calculator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@GetMapping("/api/users")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}