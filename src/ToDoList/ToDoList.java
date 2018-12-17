package ToDoList;

import java.util.List;

import common.DisplayIF;

/**
 * 
 */
public class ToDoList implements DisplayIF {

    /**
     * Default constructor
     */
    public ToDoList() {
    }

    /**
     * 
     */
    private List<Task> taskList;




    /**
     * @param name 
     * @param description
     */
    public void createTask(String name, String description) {
        // TODO implement here
    }

    /**
     * @param task
     */
    public void deleteTask(Task task) {
        // TODO implement here
    }

    /**
     * @param task 
     * @param name 
     * @param description
     */
    public void updateTask(Task task, String name, String description) {
        // TODO implement here
    }

    /**
     * @param task
     */
    public void markDone(Task task) {
        // TODO implement here
    }

	@Override
	public void display(String message) {
		// TODO Auto-generated method stub
		
	}
}