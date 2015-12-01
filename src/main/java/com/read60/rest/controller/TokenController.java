package com.read60.rest.controller;

import com.read60.rest.entity.Token;
import com.read60.rest.repository.TokenRepository;

public class TokenController extends GenericController<Token> implements TokenRepository{

	public TokenController() {
		super(Token.class);
	}

	@Override
	public Token retrieveToken(String tokenKey) {
		Token token = null;
		session = sessionFactory.openSession();
		session.beginTransaction();
		token = session.get(Token.class, tokenKey);
		session.close();
		return token;
	}

}
