package com.read60.rest.repository;

import com.read60.rest.entity.Token;

public interface TokenRepository extends GenericRepository<Token>{

	Token retrieveToken(String tokenKey);
	
}
