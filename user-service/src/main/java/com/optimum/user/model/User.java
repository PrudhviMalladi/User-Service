package com.optimum.user.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	private String userName;
	
	private String userPwd;
	
	private String userRole;
	
	private int userAge;
	
	private String userEmail;
	
	private long userContact;
	
	@Column(unique = false, nullable = false, length = 100000)
	private byte[] userImage;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(int userId, String userName, String userPwd, String userRole, int userAge, String userEmail,
			long userContact, byte[] userImage) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userRole = userRole;
		this.userAge = userAge;
		this.userEmail = userEmail;
		this.userContact = userContact;
		this.userImage = userImage;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public int getUserAge() {
		return userAge;
	}

	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public long getUserContact() {
		return userContact;
	}

	public void setUserContact(long userContact) {
		this.userContact = userContact;
	}

	public byte[] getUserImage() {
		return userImage;
	}

	public void setUserImage(byte[] userImage) {
		this.userImage = userImage;
	}
	
	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userRole=" + userRole + ", userAge=" + userAge
				+ ", userEmail=" + userEmail + ", userContact=" + userContact + ", userImage="
				+ Arrays.toString(userImage) + "]";
	}
	
}
