package AppServer;

import java.util.ArrayList;

public class TaskList {

	 protected String task;

	public TaskList() {
		
	}

	protected  ArrayList<String> list = new ArrayList<String>();
    
    public void addToList(String task) {
        this.task = task;

        list.add(task);
    }
    
    public String getList() {
       
        int counter = 0;
        task = "";
        for(String getElement : list) {
        	counter++;        	
            task = task + String.format("Tarefa %d -> %s; ", counter, getElement);
           
        }
        return task;
    }

	

	

}
