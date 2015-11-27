package com.read60.rest.repository;

import com.read60.rest.entity.Credentials;

public interface CredentialsRepository extends GenericRepository<Credentials>{

	Credentials retrieveCredentialsByUsername(String username);
	
}
