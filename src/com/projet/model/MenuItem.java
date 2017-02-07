package com.projet.model;

import java.math.BigDecimal;
public class MenuItem {
	
	private String id;
	private String name;
	private String desc;
	private BigDecimal price;
	private String categorie;
	
	public MenuItem() {
		// TODO Auto-generated constructor stub
	}

	public MenuItem(String id, String name, String desc, BigDecimal price,String categorie) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.price = price;
		this.categorie = categorie;
	}

	
	
	public String getCategorie() {
		return categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public boolean equals(Object obj) {
		MenuItem m =(MenuItem) obj;
		return this.id.equals(m.id);
	}
	
	

}