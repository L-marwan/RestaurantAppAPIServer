package com.projet.model;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order{
	private String id;
	private int table;
	private BigDecimal total;
	private Date date;
	private List<MenuItem> items;
	private String state ="new";
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;	
	}
	public List<MenuItem> getItems() {
		return items;
	}
	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	

}
