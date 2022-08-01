package com.optimum.user.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already exists")
public class DuplicateUserNameException extends Exception {

	private static final long serialVersionUID = 1L;
	
}

