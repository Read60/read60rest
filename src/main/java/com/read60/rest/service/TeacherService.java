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
import com.read60.rest.controller.TeacherController;
import com.read60.rest.entity.School;
import com.read60.rest.entity.Student;
import com.read60.rest.entity.Teacher;
import com.read60.rest.util.Util;

@Path("/teachers")
public class TeacherService {

TeacherController controller = new TeacherController();
	
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createTeacher(Teacher teacher) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		Teacher result = controller.create(teacher);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@GET
	@Secured
	@Produces("application/json")
	public Response retrieveAllTeachers() throws JsonProcessingException {
		String response = "Unsuccessful Get";
		List<Teacher> result = controller.retrieveAll();

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@GET
	@Secured
	@Path("/{id}")
	@Produces("application/json")
	public Response retrieveTeacher(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		Teacher result = controller.retrieve(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Secured
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
	@Secured
	@Path("/{id}/school")
	@Produces("application/json")
	public Response retrieveSchool(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		School result = controller.retrieveSchool(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@PUT
	@Secured
	public Response updateTeacher(Teacher teacher) {
		controller.udpate(teacher);
		return Response.status(200).entity("Okay").build();
	}

	@DELETE
	@Secured
	public Response deleteSchool(Teacher teacher) {
		controller.delete(teacher);
		return Response.status(200).entity("Okay").build();
	}
	
}
