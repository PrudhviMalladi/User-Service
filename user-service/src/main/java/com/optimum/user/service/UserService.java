package com.optimum.user.service;

import com.optimum.user.model.User;
import com.optimum.user.util.exception.DuplicateUserNameException;
import com.optimum.user.util.exception.UserNotFoundException;

public interface UserService {

	public User registerUser(User user) throws DuplicateUserNameException;
	
	public String authenticateUser(User user) throws UserNotFoundException;
	
	public User authorizeUser(String userToken);
		
}
