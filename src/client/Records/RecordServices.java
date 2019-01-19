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
     * Default constructor
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
     * @param rec 
     * @param donatingUserID
     */
    public void deleteRecord(int rec, int donatingUserID) {
        // TODO implement here
    }

    /**
     * @param id
     */
    public void readRecord(int id) {
        // TODO implement here
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

    public void handleReceivedRecord(RecordType record){
        try(FileOutputStream stream = new FileOutputStream(System.getProperty("user.home") +"/Downloads/"+ record.getName())) {
            stream.write(record.getRecord());
            client.getDisplay().setState("RECORD DOWNLOADED");
        } catch (IOException e) {
            e.printStackTrace();
            client.getDisplay().setState("RECORD NOT DOWNLOADED");
        }
    }

    public void handleRecordUploaded(String msg) {
        String state = msg.split(" ")[1];
        if(state.equalsIgnoreCase("SUCCESS"))
            client.getDisplay().setState("REC UPLOAD SUCCESS");
        else
            client.getDisplay().setState("REC UPLOAD FAILURE");
    }
}