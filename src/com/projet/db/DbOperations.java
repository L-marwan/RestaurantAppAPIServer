package com.projet.db;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.projet.model.Menu;
import com.projet.model.MenuItem;
import com.projet.model.Order;
import com.projet.util.IdGenerator;


public class DbOperations {

//---- Menu operations---------------------------	
	public static Menu getMenu () throws Exception{
		Menu menu = new Menu();
		
		Connection dbConn = null;
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch (Exception e){
				e.printStackTrace();
			}
			Statement stmt = dbConn.createStatement();
			String query = "SELECT * FROM menu_item";
			ResultSet res = stmt.executeQuery(query);
			while(res.next()){
				
				String id = res.getString(1);
				String name = res.getString(2);
				String desc = res.getString(3);
				BigDecimal price = res.getBigDecimal(4);
				String cat = res.getString(5);
				
				menu.getItems().add(new MenuItem(id, name, desc, price, cat));
			}
		} catch (SQLException sqle){
			throw sqle;
		} catch (Exception e) {
			if(dbConn != null){
				dbConn.close();
			}
			throw e;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
		
		return menu;
	}
	
	public static void addMenuItem(MenuItem item) throws SQLException{
		Connection dbConn = null;
		String insertSQL =  "INSERT INTO `menu_item`(`id`, `name`, `desc`, `price`) VALUES"
				+ "(?,?,?,?)";
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch (Exception e){
				e.printStackTrace();
			}
			PreparedStatement preparedStatement = dbConn.prepareStatement(insertSQL);
			preparedStatement.setString(1, item.getId());
			preparedStatement.setString(2, item.getName());
			preparedStatement.setString(3, item.getDesc());
			preparedStatement.setBigDecimal(4, item.getPrice());
			
			preparedStatement.executeUpdate();
			System.out.println("item: "+item.getName()+"added!!");
			
		} catch (SQLException sqle){
			printSQLException(sqle);
		} catch (Exception e) {
			if(dbConn != null){
				dbConn.close();
			}
			throw e;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
	}
	
	public static void updateMenuItem(MenuItem newItem){
		
	}
	
//------------ Order Operations ------------------------

	/**
	 * returns all orders with the state "new"
	 * @return
	 * @throws Exception 
	 */
	public static ArrayList<Order> getNewOrders() throws Exception{
		return getOrdersByState("new");
	}
	
	/**
	 * helper method to get orders by state
	 * @param state
	 * @return
	 * @throws Exception 
	 */
	private static ArrayList<Order> getOrdersByState(String state) throws Exception{
		ArrayList<Order> result = new ArrayList<>();
		
		Connection dbConn = null;
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch (Exception e){
				e.printStackTrace();
			}
			String query = "SELECT * FROM `order` WHERE `state` = ?";
			PreparedStatement stmt = dbConn.prepareStatement(query);
			stmt.setString(1, state);
			System.out.println(stmt.toString());
			ResultSet res = stmt.executeQuery();
			while(res.next()){
				
				String id = res.getString(1);
				int table = res.getInt(2);
				BigDecimal price = res.getBigDecimal(3);
				Date date = res.getTimestamp(4);
				String stt = res.getString(5);
				
				Order order = new Order();
				order.setId(id);
				order.setTable(table);
				order.setTotal(price);
				order.setDate(date);
				order.setState(stt);
				order.setItems(getOrderItems(id));
				result.add(order);
			}
		} catch (SQLException sqle){
			printSQLException(sqle);
		} catch (Exception e) {
			if(dbConn != null){
				dbConn.close();
			}
			throw e;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
		
		return result;
	}
	
	/**
	 * given an order id returns the items of the order
	 * @param orderId
	 * @return
	 * @throws Exception 
	 */
	private static ArrayList<MenuItem> getOrderItems(String orderId) throws Exception{
		ArrayList<MenuItem> result = new ArrayList<MenuItem>();
		Menu m= getMenu();
		Connection dbConn = null;
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch (Exception e){
				e.printStackTrace();
			}
			PreparedStatement stmt = dbConn.prepareStatement("SELECT item_id FROM order_items WHERE order_id= ?");
			stmt.setString(1, orderId);
			ResultSet res = stmt.executeQuery();
			while(res.next()){
				String id = res.getString(1);	
				result.add(m.getItemById(id));
			}
		} catch (SQLException sqle){
			throw sqle;
		} catch (Exception e) {
			if(dbConn != null){
				dbConn.close();
			}
			throw e;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
		return result;
	}
	/**
	 * insert order to database
	 * 
	 * @param order
	 * @throws SQLException
	 */
	
	public static void addOrder(Order order) throws SQLException{
		Connection dbConn = null;
		String insertSQL =  "INSERT INTO `order`(`id`, `table_num`, `sum`, `state`) VALUES"
				+ "(?,?,?,?)";
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch (Exception e){
				e.printStackTrace();
			}
			PreparedStatement preparedStatement = dbConn.prepareStatement(insertSQL);
			preparedStatement.setString(1, order.getId());
			preparedStatement.setInt(2, order.getTable());
			preparedStatement.setBigDecimal(3, order.getTotal());
			preparedStatement.setString(4, order.getState());
			
			preparedStatement.executeUpdate();
			System.out.println("order added!!");
			addOrderItems(order, order.getItems());
			
		} catch (SQLException sqle){
			printSQLException(sqle);
		} catch (Exception e) {
			if(dbConn != null){
				dbConn.close();
			}
			throw e;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
	}
	
	
	public static void addOrderItems(Order order, List<MenuItem>items) throws SQLException{
		Connection dbConn = null;
		String insertSQL =  "INSERT INTO `order_items`(`id`, `order_id`, `item_id`, `quantity`) VALUES "
				+ "(?,?,?,?)";
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch (Exception e){
				e.printStackTrace();
			}
			for(MenuItem item:items){
			PreparedStatement preparedStatement = dbConn.prepareStatement(insertSQL);
			preparedStatement.setString(1,IdGenerator.generate());
			preparedStatement.setString(2, order.getId());
			preparedStatement.setString(3,item.getId());
			preparedStatement.setInt(4, 4);
			
			preparedStatement.executeUpdate();
			System.out.println("order item added!!");
			}
			
		} catch (SQLException sqle){
			printSQLException(sqle);
		} catch (Exception e) {
			if(dbConn != null){
				dbConn.close();
			}
			throw e;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
	}
	
	/**
	 * change the state of all new orders of a certain table to another state
	 * @throws SQLException 
	 */
	public static void updateAllNewOrdersByTable(String newState, int tableNum) throws SQLException{
		String sql =" UPDATE `order` SET `state`=? WHERE `state` =? AND `table_num` =? ";
		Connection dbConn = null;
		try{
			try{
				dbConn = DBConnection.createConnection();
			} catch (Exception e){
				e.printStackTrace();
			}
	
			PreparedStatement preparedStatement = dbConn.prepareStatement(sql);
			preparedStatement.setString(1,newState);
			preparedStatement.setString(2, "new");
			preparedStatement.setInt(3, tableNum);
			preparedStatement.executeUpdate();
			System.out.println("order state updated!!");
	
			
		} catch (SQLException sqle){
			printSQLException(sqle);
		} catch (Exception e) {
			if(dbConn != null){
				dbConn.close();
			}
			throw e;
		} finally {
			if(dbConn != null){
				dbConn.close();
			}
		}
	}
	
	//---------
	
	public static void printSQLException(SQLException ex) {

	    for (Throwable e : ex) {
	        if (e instanceof SQLException) {


	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " +
	                    ((SQLException)e).getSQLState());

	                System.err.println("Error Code: " +
	                    ((SQLException)e).getErrorCode());

	                System.err.println("Message: " + e.getMessage());

	                Throwable t = ex.getCause();
	                while(t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	     
	    }
	}
	
	

}
