package com.projet.model;

import java.util.ArrayList;
import java.util.List;

public class Menu  {
	private List<MenuItem> items = new ArrayList<>();
	
	public Menu(){
		
	}

	public List<MenuItem> getItems() {
		return items;
	}

	public void setItems(List<MenuItem> items) {
		this.items = items;
	}
	
	public MenuItem getItemById(String id){
		MenuItem m = new MenuItem();
		m.setId(id);
		int index = items.indexOf(m);
		return items.get(index);
	}
	
			
}
