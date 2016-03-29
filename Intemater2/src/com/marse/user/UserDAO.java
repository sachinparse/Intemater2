package com.marse.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.marse.model.User;

public interface UserDAO {

	// validating user login
	public boolean authenticateUser(int userId, String password) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException,
																 NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException,
																 IllegalBlockSizeException, BadPaddingException, IOException; 
	public User getUser(int userId);          // getting current user record
	public void addUser(User objUser);                    // adding new user.
	public void deleteUser(int userId);				  // deleting an existing user by userId.
	public void changePassword();			  // Changing password of the user
	public List<User> listOfUser(int offSet, int records); // fetching list of all users
	public void updateUser(User objUser);    // updating the user details
	public int userCount();            // getting the count of all users
	
}
