package com.marse.user;

import com.marse.model.User;

public interface UserDAO {

	public boolean authenticateUser(int userId, String password); // validating user login
	public User getUser(int userId);          // getting current user record
	public void addUser();                    // adding new user.
	public void deleteUser();				  // deleting an existing user by userId.
	public void changePassword();			  // Changing password of the user
	
}
