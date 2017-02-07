package com.projet.db;

public class DataBaseListener {
	static Thread thread;
	
	public static void listner(){
		 thread= new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					
					System.out.println("listening...\n");
					try {
						if(DbOperations.getNewOrders().size()>0){
							//there are new orders do stuff
							
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		thread.start();
		
		
	}
	

}
