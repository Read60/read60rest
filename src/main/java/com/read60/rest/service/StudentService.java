package com.read60.rest.service;

import java.util.List;

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
import com.read60.rest.controller.StudentController;
import com.read60.rest.entity.Library;
import com.read60.rest.entity.ReadLog;
import com.read60.rest.entity.Student;
import com.read60.rest.util.Util;
 
@Path("/students")
public class StudentService {

	StudentController controller = new StudentController();

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createStudent(Student student) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		Student result = controller.create(student);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Secured
	@Produces("application/json")
	public Response retrieveAllStudents() throws JsonProcessingException {
		String response = "Unsuccessful Get";
		List<Student> result = controller.retrieveAll();

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@GET
	@Secured
	@Path("/{id}")
	@Produces("application/json")
	public Response retrieveStudent(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		Student result = controller.retrieve(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Secured
	@Path("/{id}/log")
	@Produces("application/json")
	public Response retrieveStudentLog(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		
		List<ReadLog> result = controller.retrieveLog(Long.parseLong(id));
		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}
	
	@GET
	@Secured
	@Path("/{id}/library")
	@Produces("application/json")
	public Response retrieveStudentLibrary(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		
		Library result = controller.retrieveLibrary(Long.parseLong(id));
		
		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);	//Throws exception when Nested Object needs to be serialized

		return Response.status(200).entity(response).build();
	}

	@PUT
	@Secured
	public Response updateStudent(Student student) {
		controller.udpate(student);
		return Response.status(200).build();
	}

	@DELETE
	@Secured
	public Response deleteStudent(Student student) {
		controller.delete(student);
		return Response.status(200).build();
	}
	
}
