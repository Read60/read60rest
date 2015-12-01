package com.read60.rest.service;

import java.util.Date;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.read60.rest.controller.CredentialsController;
import com.read60.rest.controller.TokenController;
import com.read60.rest.entity.Credentials;
import com.read60.rest.entity.Student;
import com.read60.rest.entity.Token;
import com.read60.rest.util.Util;

@Path("/")
public class GatewayService {

	CredentialsController credController = new CredentialsController();
	TokenController tokenController = new TokenController();

	@POST
	@Path("/register")
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(Credentials credentials) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		Credentials result = credController.create(credentials);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result); // Throws exception when
														// Nested Object needs
														// to be serialized

		return Response.status(200).entity(response).build();
	}

	@POST
	@Path("/authentication")
	@Produces("application/json")
	@Consumes("application/json")
	public Response authenticateUser(Credentials credentials) {

		if (credentials == null)
			return Response.status(Response.Status.UNAUTHORIZED).build();
		
		Token token = new Token();
		Student student = authenticate(credentials.getUsername());

		if (student == null)
			return Response.status(Response.Status.UNAUTHORIZED).build();

		token = issueToken(student.getId());
		return Response.ok(token).build();
	}

	private Student authenticate(String username) {
		Credentials user = credController.retrieveCredentialsByUsername(username);

		if (user == null)
			return null;

		return user.getStudent();
	}

	private Token issueToken(Long studentId) {
		Token token = new Token();
		token.setTokenKey(UUID.randomUUID().toString().replaceAll("-", ""));
		token.setStudentId(studentId);
		token.setCreated(new Date());
		tokenController.create(token);
		return token;
	}

}
