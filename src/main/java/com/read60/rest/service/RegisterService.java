package com.read60.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.read60.rest.controller.CredentialsController;
import com.read60.rest.entity.Credentials;
import com.read60.rest.util.Util;

@Path("/register")
public class RegisterService {

	CredentialsController controller = new CredentialsController();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response register(Credentials credentials) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		Credentials result = controller.create(credentials);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
}
