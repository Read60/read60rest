/*package com.read60.rest.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.read60.rest.controller.GenericController;
import com.read60.rest.util.Util;

@SuppressWarnings("rawtypes")
@Path("/")
public class BaseService<T, J extends GenericController<T>> {

	GenericController controller;
	
	public BaseService(Class<J> controller) throws InstantiationException, IllegalAccessException {
		this.controller = controller.newInstance();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createStudent(T entity) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		@SuppressWarnings("unchecked")
		T result = (T) controller.create(entity);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveAllStudents() throws JsonProcessingException {
		String response = "Unsuccessful Get";
		@SuppressWarnings("unchecked")
		List<T> result = controller.retrieveAll();

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
}
*/