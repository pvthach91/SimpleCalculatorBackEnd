package com.pvthach.calculator.controller;

import com.pvthach.calculator.message.request.LoginForm;
import com.pvthach.calculator.message.request.SignUpForm;
import com.pvthach.calculator.message.response.EnumResponse;
import com.pvthach.calculator.message.response.JwtResponse;
import com.pvthach.calculator.message.response.ResponseMessage;
import com.pvthach.calculator.model.Role;
import com.pvthach.calculator.model.RoleName;
import com.pvthach.calculator.model.User;
import com.pvthach.calculator.repository.RoleRepository;
import com.pvthach.calculator.repository.UserRepository;
import com.pvthach.calculator.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by THACH-PC
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private int USERNAME_MAX = 50;
	private int EMAIL_MAX = 50;
	private int PASSWORD_MAX = 100;

	private int USERNAME_MIN = 3;
	private int EMAIL_MIN = 3;
	private int PASSWORD_MIN = 3;

	@Autowired
    AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
    PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
		// Validate username
		String username = signUpRequest.getUsername();
		if (StringUtils.isEmpty(username) || username.length() < USERNAME_MIN || username.length() > USERNAME_MAX ) {
			return new ResponseEntity<>(new ResponseMessage(EnumResponse.USERNAME_INVALID.getDescription()),
					HttpStatus.BAD_REQUEST);
		}
		if (userRepository.existsByUsername(username)) {
			return new ResponseEntity<>(new ResponseMessage(EnumResponse.USERNAME_EXIST.getDescription()),
					HttpStatus.BAD_REQUEST);
		}

		// Validate email
		String email = signUpRequest.getEmail();
		if (StringUtils.isEmpty(email) || email.length() < EMAIL_MIN || email.length() > EMAIL_MAX ) {
			return new ResponseEntity<>(new ResponseMessage(EnumResponse.EMAIL_INVALID.getDescription()),
					HttpStatus.BAD_REQUEST);
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage(EnumResponse.EMAIL_EXIST.getDescription()),
					HttpStatus.BAD_REQUEST);
		}

		// Validate password
		String password = signUpRequest.getPassword();
		if (StringUtils.isEmpty(password) || password.length() < PASSWORD_MIN || password.length() > PASSWORD_MAX ) {
			return new ResponseEntity<>(new ResponseMessage(EnumResponse.PASSWORD_INVALID.getDescription()),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();

		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
				.orElseThrow(() -> new RuntimeException(EnumResponse.ROLE_NOT_FOUND.getDescription()));
		roles.add(userRole);

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage(EnumResponse.USER_REGISTERED_SUCCESS.getDescription()), HttpStatus.OK);
	}
}