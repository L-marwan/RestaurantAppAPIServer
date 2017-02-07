package com.projet.api;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.projet.db.DbOperations;
import com.projet.model.Order;



/**
 * veeeeeery bad solution until I figure out how to implement SSE
 * this should do for now 
 * @author marwan
 *
 */
@Path ("/check")
public class CheckForOrders {
	
	
	@GET //never forget to specify the request type EVER AGAIN !!
	@Path("/{tableNum}")
	@Produces({MediaType.APPLICATION_JSON})
	public String getNewOrders(@PathParam("tableNum") int tableNum) throws Exception{
		
		System.out.println(tableNum);
		ArrayList<Order> newOrders = DbOperations.getNewOrders();
		ArrayList<Order> filterByTableNum = new ArrayList<>();
		for(Order o: newOrders){
			if(o.getTable() == tableNum) filterByTableNum.add(o);
		}
		DbOperations.updateAllNewOrdersByTable("waiting", tableNum);
		System.out.println(new Gson().toJson(filterByTableNum));
		if( filterByTableNum.size()>0) return new Gson().toJson(filterByTableNum);
		
		return "no new orders";
	}

}
