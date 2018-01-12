package com.accolite.miniAU.restCRUD.resource;

import java.security.Provider.Service;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.accolite.miniAU.restCRUD.model.Model;
import com.accolite.miniAU.restCRUD.service.Services;

@Path("employee")
public class Resources {
	
	Services ser = new Services();
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Model> getMembers()
	{
		return ser.getMembers();
	}
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Model InsertingEmployee(Model model)
	{
		return ser.AddEmployee(model);
	}
	
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void Delete(@PathParam("id") int id)
	{
		ser.DeleteEmployee(id);
	}
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Model Update(Model model)
	{
		return ser.UpdateEmployee(model);
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Model Retrive(@PathParam("id") int id)
	{
		return new Services().RetriveEmployee(id);
	}

}
