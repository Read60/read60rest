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
import com.read60.rest.controller.BookController;
import com.read60.rest.entity.Book;
import com.read60.rest.util.Util;

@Secured
@Path("/books")
public class BookService {

	BookController controller = new BookController();

	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response createBook(Book book) throws JsonProcessingException {
		String response = "Unsuccessful Post";
		Book result = controller.create(book);

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);

		return Response.status(200).entity(response).build();
	}

	@GET
	@Produces("application/json")
	public Response retrieveAllBooks() throws JsonProcessingException {
		String response = "Unsuccessful Get";
		List<Book> result = controller.retrieveAll();

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);

		return Response.status(200).entity(response).build();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Response retrieveBook(@PathParam("id") String id) throws JsonProcessingException {
		String response = "Unsuccessful Get";
		Book result = controller.retrieve(Long.parseLong(id));

		ObjectWriter writer = Util.mapper.writer();
		response = writer.writeValueAsString(result);

		return Response.status(200).entity(response).build();
	}

	@PUT
	public Response updateBook(Book book) {
		controller.udpate(book);
		return Response.status(200).entity("Okay").build();
	}

	@DELETE
	public Response deleteBook(Book book) {
		controller.delete(book);
		return Response.status(200).entity("Okay").build();
	}

}
