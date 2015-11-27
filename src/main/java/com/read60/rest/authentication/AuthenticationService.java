package com.read60.rest.authentication;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import com.read60.rest.controller.CredentialsController;
import com.read60.rest.entity.Credentials;

public class AuthenticationService {
	
	CredentialsController controller = new CredentialsController();
	
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		Credentials credentials = controller.retrieveCredentialsByUsername(username);
		boolean authenticationStatus = false;
		if(credentials != null && credentials.getPassword().equals(password))
			authenticationStatus = true;
		
		return authenticationStatus;
	}
}