package client.Courses;

import client.CoreClient;
import java.io.IOException;


public class CourseServices {
	private CoreClient client;
	
	 public CourseServices(CoreClient coreClient){
		 client = coreClient;
	 }
	 
	 /**
	 * This method creates String with all course attributes and send it to the server to create the course
	 * @param name : course name
	 * @param description : small description of the course
	 * @param totalHours : the total hours of the course
	 * @param promotion : the promotion related to the course
	 * @param referingTeacher : the referring teacher of the course
	 */
	public void handleCreateCourse (String name, String description, int totalHours, int idT){
		String objConstruct = name + "-/-" + description + "-/-" + totalHours + "-/-" + idT;
		try {
	    	client.getConnection().sendToServer("#CREATECOURSE-/-" + objConstruct);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
     * This method create String with all course attributes and send it to the server to update the course
     * @param name : course name
	 * @param description : small description of the course
	 * @param totalHours : the total hours of the course
	 * @param promotion : the promotion related to the course
	 * @param referingTeacher : the referring teacher of the course
     */
    public void handleUpdateCourse (int id, String name, String description, int totalHours, int idT){
        String objConstruct = id + "-/-" + name + "-/-" + description + "-/-" + totalHours + "-/-" + idT;

        try {
            client.getConnection().sendToServer("#UPDATECOURSE-/-" + objConstruct);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method send to the server to delete the course
     * @param id : course id
     */
    public void handleDeleteCourse (int id){
        try {
            client.getConnection().sendToServer("#DELETECOURSE-/-" + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * This method split the server message to see if the creation is a success or a failure. It change a state for the UI.
     * @param msg : String with the result of creation
     */
    public void handleCreatedCourse(String msg) {
        String args[] = msg.split(" ");
        if(args[1].equalsIgnoreCase("SUCCESS"))
            client.getDisplay().setState("CC SUCCESS");
        else
            client.getDisplay().setState("CC FAILURE");
    }
    
    /**
     * This method split the server message to see if the deletion is a success or a failure. It change a state for the UI.
     * @param msg : String with the result of deletion
     */
    public void handleDeletedCourse(String msg){
        String args[] = msg.split(" ");
        if (args[1].equalsIgnoreCase("SUCCESS"))
            client.getDisplay().setState("CD SUCCESS");
        else
            client.getDisplay().setState("CD FAILURE");
    }
    
    /**
     * This method split the server message to sse if the update is a success or a failure. It change a state for the UI.
     * @param msg : String with the result of deletion
     */
    public void  handleUpdatedCourse (String msg){
        String args[] = msg.split(" ");
        if (args[1].equalsIgnoreCase("SUCCESS")){
            client.getDisplay().setState("CU SUCCESS");
        } else {
            client.getDisplay().setState("CU FAILURE");
        }
    }

    /**
     * This method send a message to the server to have the courses list
     */
    public void getCourses() {
        try {
        	System.out.println("ok3");
            client.getConnection().sendToServer("#GETCOURSES" );
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
	
}