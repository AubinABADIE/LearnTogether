package client.Events;

import java.io.IOException;
import java.sql.Date;

import client.CoreClient;

/**
 * This is the business logic related to the courses on the client side.
 * @author Solene SERAFIN
 */

public class EventServices {
	private CoreClient client;
	
	 public EventServices(CoreClient coreClient){
		 client = coreClient;
	 }
	 
	 /**
	 * This method creates String with all event attributes and send it to the server to create the event
     * @param dateTimeEvent : the time and the date when the event begin
     * @param duration : the duration of the event
     * @param idRoom : the room when the event will take place
     * @param idCourse : the course related to the event 
     * @param idTeacher : the teacher related to the event
     * @param idClass : the class related to the event
     * @param idPromo : the promo related to the event
     * @param iddepartement : the departement related to the event
	 */
	public void handleCreateEvent (Date dateTimeEvent, float duration, int idRoom, int idCourse, int idTeacher, int idClass, int idPromo, int idDepartement){
		String objConstruct = dateTimeEvent + "-/-" + duration + "-/-" + idRoom + "-/-" + idCourse + "-/-" + idTeacher + "-/-" + idClass + "-/-" + idPromo + "-/-" + idDepartement;
		try {
	    	client.getConnection().sendToServer("#CREATEEVENT-/-" + objConstruct);
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	
	/**
    * This method create String with all event attributes and send it to the server to update the event
    * @param idEvent : the id of the Event
    * @param dateTimeEvent : the time and the date when the event begin
    * @param duration : the duration of the event
    * @param idRoom : the room when the event will take place
    * @param idCourse : the course related to the event 
    * @param idTeacher : the teacher related to the event
    * @param idClass : the class related to the event
    * @param idPromo : the promo related to the event
    * @param iddepartement : the departement related to the event
    */
   public void handleUpdateEvent (int idEvent, Date dateTimeEvent, float duration, int idRoom, int idCourse, int idTeacher, int idClass, int idPromo, int idDepartement){
       String objConstruct = idEvent + "-/-" + dateTimeEvent + "-/-" + duration + "-/-" + idRoom + "-/-" + idCourse + "-/-" + idTeacher + "-/-" + idClass + "-/-" + idPromo + "-/-" + idDepartement;

       try {
           client.getConnection().sendToServer("#UPDATEEVENT-/-" + objConstruct);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   /**
    * This method send to the server to delete the event
    * @param id : event id
    */
   public void handleDeleteEvent (int idEvent){
       try {
           client.getConnection().sendToServer("#DELETEEVENT-/-" + idEvent);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
   
   /**
    * This method split the server message to see if the creation is a success or a failure. It change a state for the UI.
    * @param msg : String with the result of creation
    */
   public void handleCreatedEvent(String msg) {
       String args[] = msg.split(" ");
       if(args[1].equalsIgnoreCase("SUCCESS"))
           client.getDisplay().setState("EC SUCCESS");
       else
           client.getDisplay().setState("EC FAILURE");
   }
   
   /**
    * This method split the server message to see if the deletion is a success or a failure. It change a state for the UI.
    * @param msg : String with the result of deletion
    */
   public void handleDeletedEvent(String msg){
       String args[] = msg.split(" ");
       if (args[1].equalsIgnoreCase("SUCCESS"))
           client.getDisplay().setState("ED SUCCESS");
       else
           client.getDisplay().setState("ED FAILURE");
   }
   
   /**
    * This method split the server message to sse if the update is a success or a failure. It change a state for the UI.
    * @param msg : String with the result of deletion
    */
   public void  handleUpdatedEvent (String msg){
       String args[] = msg.split(" ");
       if (args[1].equalsIgnoreCase("SUCCESS")){
           client.getDisplay().setState("EU SUCCESS");
       } else {
           client.getDisplay().setState("EU FAILURE");
       }
   }

   /**
    * This method sends a message to the server to have the events list of the teacher (came from TeacherUI)
    * @param userID : user id
    */
   
   public void getEvents(int userID) {
       try {
           client.getConnection().sendToServer("#GETEVENTT-/-"+ userID );
       } catch (IOException e) {
           e.printStackTrace();
       }

   }

   /**
    * This method sends a message to the server to have the events list (came from adminUI)
    */
   public void getEvents() {
       try {
           client.getConnection().sendToServer("#GETEVENTS" );
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
}
