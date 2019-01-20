package client.Records;


import Types.RecordType;
import client.CoreClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * This function handles ecverything related to the records on the client side.
 * @author Marie SALELLES
 * @author Yvan SANSON
 */
public class RecordServices {
    private CoreClient client;

    /**
     * Constructor
     * @param client the main business logic.
     */
    public RecordServices(CoreClient client) {
        this.client=client;
    }


    /**
     * This method creates a RecordType to send to the server. It encapsulates every parameters.
     * @param courseID the ID of the course from which the record originated.
     * @param examYear the year the exam was given.
     * @param record the actual file.
     * @param donatingUser the user that donated the record.
     */
    public void createRecord(int courseID, int examYear, File record, int donatingUser) {
        System.out.println("Records OK");
        try {
            byte[] recordToByteArray = Files.readAllBytes(record.toPath());
            RecordType recordObj = new RecordType(record.getName(), courseID, examYear, recordToByteArray,donatingUser);
            client.getConnection().sendToServer(recordObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method send to server the request to have the records
     */
    public void getAllRecord(){
        try {
            client.getConnection().sendToServer("#GETRECORDS" );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method send to the server the download request
     * @param id : record id
     */
    public void downloadRec(int id){
        try {
            client.getConnection().sendToServer("#DOWNLOADRECORD " + id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method change the state for the UI
     * @param record : the record
     */
    public void handleReceivedRecord(RecordType record){
        try(FileOutputStream stream = new FileOutputStream(System.getProperty("user.home") +"/Downloads/"+ record.getName())) {
            stream.write(record.getRecord());
            client.getDisplay().setState("RECORD DOWNLOADED");
        } catch (IOException e) {
            e.printStackTrace();
            client.getDisplay().setState("RECORD NOT DOWNLOADED");
        }
    }

    /**
     * This method handle the Server response to display correctly on the UI
     * @param msg : String result of the uploading
     */
    public void handleRecordUploaded(String msg) {
        String state = msg.split(" ")[1];
        if(state.equalsIgnoreCase("SUCCESS"))
            client.getDisplay().setState("REC UPLOAD SUCCESS");
        else
            client.getDisplay().setState("REC UPLOAD FAILURE");
    }

    /**
     * This method handle the client request to have the user record list
     * @param id: user id
     */
    public void getRecordsByUser(int id){
        try {
            client.getConnection().sendToServer("#GETRECORDBYUSER" +"-/-"+ id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handle the client request to delete a record
     * @param id : record id
     */
    public void handleDeleteRecord(int id){
        try {
            client.getConnection().sendToServer("#DELETERECORD" +"-/-"+ id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method handle the server response to display correctly on the UI
     * @param msg : state of the deletion
     */
    public void handleDeletedRecord(String msg){
        String state = msg.split(" ")[1];
        if(state.equalsIgnoreCase("SUCCESS"))
            client.getDisplay().setState("REC DELETE SUCCESS");
        else
            client.getDisplay().setState("REC DELETE FAILURE");
    }
}