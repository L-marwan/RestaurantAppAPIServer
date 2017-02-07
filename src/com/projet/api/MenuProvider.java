package com.projet.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.projet.db.DbOperations;
import com.projet.model.Menu;


@Path("menu")
public class MenuProvider {
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public Menu getMenu(@Context HttpServletRequest request) throws Exception{
		System.out.print("got request"+ request.getRemoteAddr()+"\n");
		return DbOperations.getMenu();
	}

}
