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
import com.read60.rest.controller.SchoolController;
import com.read60.rest.entity.School;
import com.read60.rest.entity.Student;
import com.read60.rest.entity.Teacher;
import com.read60.rest.util.Util;

@Secured
@Path("/schools")
public class SchoolService {
	
	SchoolController controller = new SchoolController();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createSchool(School school) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		School result = controller.create(school);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@GET
	@Produces("application/json")
	public Response retrieveAllSchools() throws JsonProcessingException {
		String response = null;
		List<School> result = controller.retrieveAll();

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response retrieveSchool(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		School result = controller.retrieve(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/{id}/students")
	@Produces("application/json")
	public Response retrieveStudents(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		Set<Student> result = controller.retrieveStudents(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Path("/{id}/teachers")
	@Produces("application/json")
	public Response retrieveSchoolTeachers(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		Set<Teacher> result = controller.retrieveTeachers(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@PUT
	public Response updateSchool(School school) {
		controller.udpate(school);
		return Response.status(200).entity("Okay").build();
	}

	@DELETE
	public Response deleteSchool(School school) {
		controller.delete(school);
		return Response.status(200).entity("Okay").build();
	}
}
