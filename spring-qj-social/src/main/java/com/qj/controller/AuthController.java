package com.qj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qj.config.JwtProvider;
import com.qj.models.User;
import com.qj.repository.UserRepository;
import com.qj.request.LoginRequest;
import com.qj.response.AuthResponse;
import com.qj.service.CustomerUserDetailService;
import com.qj.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomerUserDetailService customerUserDetailService;

	// /auth/signup
	@PostMapping("/register")
	public AuthResponse createUser(@RequestBody User user) throws Exception {

		User isExist = userRepository.findByEmail(user.getEmail());

		if (isExist != null) {
			throw new Exception("Email already been used.");
		}

		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
//		newUser.setFollowers(user.getFollowers());
//		newUser.setFollowList(user.getFollowList());
//		newUser.setGender(user.getGender());
//		newUser.setId(user.getId());

		User savedUser = userRepository.save(newUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Registered successfully");

		return res;
	}

	// /auth/login
	@PostMapping("/login")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) throws Exception {

		Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

		String token = JwtProvider.generateToken(authentication);

		AuthResponse res = new AuthResponse(token, "Login successfully");

		return res;
	}

	private Authentication authenticate(String email, String password) throws Exception {
		UserDetails userDetails = customerUserDetailService.loadUserByUsername(email);

		if (userDetails == null) {
			throw new Exception("Invalid username");
		}

		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Password not matched");
		}

		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
