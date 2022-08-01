package com.optimum.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optimum.user.model.User;
import com.optimum.user.repository.UserRepository;
import com.optimum.user.util.ImageUtility;
import com.optimum.user.util.JwtTokenUtil;
import com.optimum.user.util.exception.DuplicateUserNameException;
import com.optimum.user.util.exception.UserNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public User registerUser(User user) throws DuplicateUserNameException {
		Optional<User> userOptional = userRepository.findByUserName(user.getUserName());
		if(userOptional.isEmpty()) {
			user.setUserImage(ImageUtility.compressImage(user.getUserImage()));
			return userRepository.save(user);
		}
		throw  new DuplicateUserNameException();
	}

	@Override
	public String authenticateUser(User user) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findByUserNameAndUserPwd(user.getUserName(), user.getUserPwd());
		if(userOptional.isPresent()) {
			User authenticatedUser = userOptional.get();
			return jwtTokenUtil.generateToken(authenticatedUser);
		}
		throw new UserNotFoundException();
	}

	@Override
	public User authorizeUser(String userToken) {
		User validatedUser = null;
		try {
			String userName = jwtTokenUtil.validateToken(userToken);
			Optional<User> validatedUserOpt = userRepository.findByUserName(userName);
			if(validatedUserOpt.isPresent()) {
				return validatedUserOpt.get();
			}
		} catch (Exception ex) {
			//TODO exception on decode
		}
		return validatedUser;
	}

}
