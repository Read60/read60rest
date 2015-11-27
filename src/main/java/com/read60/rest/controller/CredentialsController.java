package com.read60.rest.controller;

import org.hibernate.Query;

import com.read60.rest.entity.Credentials;
import com.read60.rest.repository.CredentialsRepository;

public class CredentialsController extends GenericController<Credentials> implements CredentialsRepository {

	public CredentialsController() {
		super(Credentials.class);
	}

	public Credentials retrieveCredentialsByUsername(String username) {
		Credentials credentials = null;
		
		session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Credentials creds WHERE username=:username");
		q.setParameter("username", username);
		if(!q.list().isEmpty())
			credentials = (Credentials) q.list().get(0);
		
		return credentials;
	}

}
