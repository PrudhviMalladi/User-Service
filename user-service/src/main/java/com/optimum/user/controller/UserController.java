package com.optimum.user.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.optimum.user.model.User;
import com.optimum.user.service.UserService;
import com.optimum.user.util.exception.DuplicateUserNameException;
import com.optimum.user.util.exception.UserNotFoundException;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value = "/register", 
			consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Object> registerUser(@RequestPart("user") User user,
			@RequestPart("file") List<MultipartFile> file) {
		User registeredUser = null;
		try {
			user.setUserImage(file.get(0).getBytes());
			registeredUser = userService.registerUser(user);
		} catch (DuplicateUserNameException e) {
			//TODO
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return new ResponseEntity<>(ObjectUtils.isEmpty(registeredUser) ? HttpStatus.CONFLICT : HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		String generatedJWT = null;
		try {
			generatedJWT = userService.authenticateUser(user);
		} catch (UserNotFoundException e) {
		}
		return new ResponseEntity<>(generatedJWT, ObjectUtils.isEmpty(generatedJWT) ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
	}
	
	@GetMapping("/authorize")
	public ResponseEntity<Object> authorizeUser(@RequestHeader("Authorization") String bearerToken) {
		User authorizedUser = userService.authorizeUser(bearerToken);
		return new ResponseEntity<>(authorizedUser, ObjectUtils.isEmpty(authorizedUser) ? HttpStatus.UNAUTHORIZED : HttpStatus.OK);
	}
}
