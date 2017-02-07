package com.projet.api;


import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.projet.db.DbOperations;
import com.projet.model.Order;
import com.projet.util.IdGenerator;

//class that handles post requests 
@Path("/post")
public class CommandReciever {
	
	
	/**
	 * getting the new order from the client side and saving it to the DB
	 * todo: trigger event to notify waiter
	 * @param cmd
	 * @return
	 */

	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getNewCmd(Order cmd){
		
		//store order in database 
		if(cmd != null){
		 try {
			 cmd.setId(IdGenerator.generate());
			DbOperations.addOrder(cmd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		System.out.println("cmd recu ");
		
		
		String reqState = "commande recu";
		return reqState;
	}
}
