package com.read60.rest.service;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.read60.rest.authentication.Secured;
import com.read60.rest.controller.ParentController;
import com.read60.rest.entity.Parent;
import com.read60.rest.entity.Student;
import com.read60.rest.util.Util;

//@Secured //TODO: Uncomment for security
@Path("/parents")
public class ParentService {

	ParentController controller = new ParentController();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createParent(Parent parent) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		Parent result = controller.create(parent);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@GET
	@Produces("application/json")
	public Response retrieveAllParents() throws JsonProcessingException {
		String response = "Unsuccessful Get";
		List<Parent> result = controller.retrieveAll();

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response retrieveParent(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		Parent result = controller.retrieve(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/{id}/students")
	@Produces("application/json")
	public Response retrieveChildren(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		Set<Student> result = controller.retrieveChildren(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@PUT
	public Response updateParent(Parent parent) {
		controller.udpate(parent);
		return Response.status(200).entity("Okay").build();
	}

	@DELETE
	public Response deleteParent(Parent parent) {
		controller.delete(parent);
		return Response.status(200).entity("Okay").build();
	}
	
}
