package com.optimum.user.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User with specified detail is not found")
public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
}
