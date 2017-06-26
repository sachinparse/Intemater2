package com.marse.credentials;

import com.marse.model.EmailCredentials;

public interface EmailCredentialsDAO {
	
	public EmailCredentials getCredentials(int credentialsId);

}
