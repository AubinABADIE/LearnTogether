
# Learn Together  Read Me 

# Launch instruction 
## Step 1 
Download the application src. If you have an IDE you can import a new project with the learn together src. 
## Step 2 
When the project is imported in the IDE, you must to install the libraries. All the libraries are in the lib folder. 

 - If the IDE is Eclipse : 
Right click on project then click on build path , configure build path and add external Jars. 
 - If the IDE is IntelliJ : 
 Click on file, then project structure, libraries and choose the libraries. 
## Step 3
Launch the server console to start the application server. 
## Step 4 
Lauch the startUI to connect a client. 
## Remarks 
To see the different user interfaces according to the different user types you have to connect with different login. 
There are 4 interfaces: 
 - The student interface, 
 - The teacher interface,
 - The administrator interface,
 - The super administrator interface. 
You cannot create a user at this time (unless you are connected as an administrator), so you must use the already created users in the data base. You can find them at the end of this document.
If you create a user with the administrator or super aministrator account you can connect to this new account  but you have to click on the *first connection* button to configurate the user's password. Finally, you are redirected to the connection page and you can enter your login and password to connect.  

  

# Protocol 
  
This part is used to provide a better understanding of the different commands and the communication protocol between the server and the clients.  
  
  
# FROM the client  
  
## #LOGIN  
Used when the client wants to connect to the server. With this command, the server checks the database to find the user's credentials and whether grand or not the access.  
*Usage*: `#LOGIN {email} {password}`  
  
## #FIRSTPWD  
Used when the client wants to change its password **for the first time** (he hasn't yet connected, and in the DB, the password is blank).  
*Usage*: `#FIRSTCONN {email} {password}`  
  
## #CREATEROOM  
Used when the client wants to create a room into the database. The parse method for the different parameters is "-/-"    
Arguments:  
- name: String  
- capacity: int  
-building: int  
- hasProjector: boolean  
- hadComputer: boolean  
- description: String  
  
*Usage*: `#CREATEROOM-/-{name}-/-{capacity}-/-{building}-/-{hasProjector}-/-{hasComputer}-/-{description}`  
  
## #DELETEROOM  
Used when the client wants to delete a room. The parse method for the different parameters is "-/-"  
Arguments:  
- id : int  
  
*Usage*: `#CRATEROOM-/-{id}`  
  
## #UPDATEROOM  
Used when the client wants to update a room into the database. The parce method for the different parameters is "-/-"  
Arguments:  
- id : int  
- name: String  
- capacity: int  
-building: int  
- hasProjector: boolean  
- hadComputer: boolean  
- description: String  
  
*Usage* : `#UPDATEROOM-/-{id}-/-{name}-/-{capacity}-/-{building}-/-{hasProjector}-/-{hasComputer}-/-{description}`  
  
## #GETROOMS  
Used when the client wants the list of all rooms.  
No parameters.  
  
## #SENDMSGTOCLIENT  
Used when the client wants to send a message to another user through the server. The parse method for the different parameters is "-/-"    
Arguments:  
- id: the sender's id.  
- email: the receiver's email.   
- message: the message sent.  
 *Usage*: `#LOGIN {id} {email} {message}`  
   
 ## #RETRIEVECONVERSATION  
 Used when the client wants to retrieve all of the conversation between him and another client.    
 Arguments:  
 - id: int, the client id  
 - email: String, the other person's email.  
 *Usage*: `#RETRIEVECONVERSATION {id} {email}`  
   
 ## #GETCONVEMAIL  
 Used when the client wants to get all its discussions.  
 Arguments:  
 - id: int the asking ID    
 *Usage*:`#GETCONVEMAIL {id}`  
   
 ## #DELETECONVERSATION  
 The client asks to delete a conversation with this command.  
 Arguments:   
 - id: int, the asking ID  
 - email: String, the other email.    
 *Usage*: `#DELETECONVERSATION {id} {email}`  
  
 ## #GETRECORDS  
 The client send this to the server to receive the record list.  
 No arguments.  
 *Usage*: `#GETRECORDS`  
  
 ## #DOWNLOADRECORD  
 The client want to retrieve a record.  
 Argument :  
   - id: int, the record id  
   *Usage*: `#DDOWNLOADRECORD {id}`  
  
  
# FROM the server  
  
  
## \#LOGON   
  
The server responds to a #LOGIN injunction from the client by sending this to the client. It is accompanied by several arguments:  
  
 - isConnected: boolean, true if the connection has been successful or false otherwise  
 - id: int the user id from the DB  
 - role: String the user role in the application.  
*Usage*:`#LOGON {isConnected} {id} {role}`  
  
## \#FIRSTCONN  
The server responds to a #FIRSTCONN injunction from the client by sending this.  
Arguments :  
- hasSucceeded : boolean, true if it was the first connection and the password has been defined, false otherwise.  
  
*Usage*:`#FIRSTCONN {hasConected}`  
  
## \#CREATEDROOM  
The server responds to a #CREATEROOMinjonction from the client by sending this.  
Arguments :  
- mess : String with the result of the action.  
  
*Usage*: `#CREATEDROOM {mess}`  
  
 ## #DELETEROOM  
 The server responds to a #DELETEROOM from the client by sending this.  
 Arguments:  
 - mess : String with the result of the action.  
  
 *Usage*:  `#DELETEROOM {mess}`  
  
## \#MESSAGE  
The server responds to a #SENDMSGTOCLIENT demand with this. It has two states, either sent (the message is in the DB) or error (the message cannot be put into the DB)    
*Usage*:`#MESSAGE {State}`  
  
## \#DELETEDCONVERSATION  
The server responds to a #DELETECONVERSATION demand with this. it has two states, either success or failure.  
*Usage*:`#DELETEDCONVERSATION {state}`  
  
## #RECORDUPLOAD  
When the server receives a RecordType to store in the database and storage service, it responds with this message. The message is either SUCCESS or FAILURE.    
*Usage*:`#RECORDUPLOAD {state}`  
  
#Users in the DB  
| id       |      name     |  first name |           email                   |   password   | role     |  
|----------|:-------------:|:-----------:|:---------------------------------:|:------------:|:--------:|  
| 3        |  SANSON       | Yvan       | yvan.sanson@etu.umontpellier.fr    |LearnTogether |STUDENT   |        
| 4        |    SALELLES   |   Marie    | marie.salelles@etu.umontpellier.fr |Test          |STUDENT   |  
| 6        | Teacher       |    PolyMtp | teacher@umontpellier.fr            |Teacher       |TEACHER   |  
| 8        | Staff         | PolyMtp    | StaffAdmin@umontpellier.fr         |AdmStaff      |ADMIN     |  
| 9        | SuperStaff    | PolyMtp    | SuperAdminAdmin@umontpellier.fr    |SuperAdmin    |SUPERADMIN|  
| 11       | Stratulat     | Tiberiu    | tibi.stratulat@umontpellier.fr     |Tibi          |TEACHER   |# Learn Together  
  
This readme is used to provide a better understanding of the different commands and the communication protocol between the server and the clients.  
  
  
# FROM the client  
  
## #LOGIN  
Used when the client wants to connect to the server. With this command, the server checks the database to find the user's credentials and whether grand or not the access.  
*Usage*: `#LOGIN {email} {password}`  
  
## #FIRSTPWD  
Used when the client wants to change its password **for the first time** (he hasn't yet connected, and in the DB, the password is blank).  
*Usage*: `#FIRSTCONN {email} {password}`  
  
## #CREATEROOM  
Used when the client wants to create a room into the database. The parse method for the different parameters is "-/-"    
Arguments:  
- name: String  
- capacity: int  
-building: int  
- hasProjector: boolean  
- hadComputer: boolean  
- description: String  
  
*Usage*: `#CREATEROOM-/-{name}-/-{capacity}-/-{building}-/-{hasProjector}-/-{hasComputer}-/-{description}`  
  
## #DELETEROOM  
Used when the client wants to delete a room. The parse method for the different parameters is "-/-"  
Arguments:  
- id : int  
  
*Usage*: `#CRATEROOM-/-{id}`  
  
## #UPDATEROOM  
Used when the client wants to update a room into the database. The parce method for the different parameters is "-/-"  
Arguments:  
- id : int  
- name: String  
- capacity: int  
-building: int  
- hasProjector: boolean  
- hadComputer: boolean  
- description: String  
  
*Usage* : `#UPDATEROOM-/-{id}-/-{name}-/-{capacity}-/-{building}-/-{hasProjector}-/-{hasComputer}-/-{description}`  
  
## #GETROOMS  
Used when the client wants the list of all rooms.  
No parameters.  
  
## #SENDMSGTOCLIENT  
Used when the client wants to send a message to another user through the server. The parse method for the different parameters is "-/-"    
Arguments:  
- id: the sender's id.  
- email: the receiver's email.   
- message: the message sent.  
 *Usage*: `#LOGIN {id} {email} {message}`  
   
 ## #RETRIEVECONVERSATION  
 Used when the client wants to retrieve all of the conversation between him and another client.    
 Arguments:  
 - id: int, the client id  
 - email: String, the other person's email.  
 *Usage*: `#RETRIEVECONVERSATION {id} {email}`  
   
 ## #GETCONVEMAIL  
 Used when the client wants to get all its discussions.  
 Arguments:  
 - id: int the asking ID    
 *Usage*:`#GETCONVEMAIL {id}`  
   
 ## #DELETECONVERSATION  
 The client asks to delete a conversation with this command.  
 Arguments:   
 - id: int, the asking ID  
 - email: String, the other email.    
 *Usage*: `#DELETECONVERSATION {id} {email}`  
  
 ## #GETRECORDS  
 The client send this to the server to receive the record list.  
 No arguments.  
 *Usage*: `#GETRECORDS`  
  
 ## #DOWNLOADRECORD  
 The client want to retrieve a record.  
 Argument :  
   - id: int, the record id  
   *Usage*: `#DDOWNLOADRECORD {id}`  
  
  
# FROM the server  
  
  
## \#LOGON   
  
The server responds to a #LOGIN injunction from the client by sending this to the client. It is accompanied by several arguments:  
  
 - isConnected: boolean, true if the connection has been successful or false otherwise  
 - id: int the user id from the DB  
 - role: String the user role in the application.  
*Usage*:`#LOGON {isConnected} {id} {role}`  
  
## \#FIRSTCONN  
The server responds to a #FIRSTCONN injunction from the client by sending this.  
Arguments :  
- hasSucceeded : boolean, true if it was the first connection and the password has been defined, false otherwise.  
  
*Usage*:`#FIRSTCONN {hasConected}`  
  
## \#CREATEDROOM  
The server responds to a #CREATEROOMinjonction from the client by sending this.  
Arguments :  
- mess : String with the result of the action.  
  
*Usage*: `#CREATEDROOM {mess}`  
  
 ## #DELETEROOM  
 The server responds to a #DELETEROOM from the client by sending this.  
 Arguments:  
 - mess : String with the result of the action.  
  
 *Usage*:  `#DELETEROOM {mess}`  
  
## \#MESSAGE  
The server responds to a #SENDMSGTOCLIENT demand with this. It has two states, either sent (the message is in the DB) or error (the message cannot be put into the DB)    
*Usage*:`#MESSAGE {State}`  
  
## \#DELETEDCONVERSATION  
The server responds to a #DELETECONVERSATION demand with this. it has two states, either success or failure.  
*Usage*:`#DELETEDCONVERSATION {state}`  
  
## #RECORDUPLOAD  
When the server receives a RecordType to store in the database and storage service, it responds with this message. The message is either SUCCESS or FAILURE.    
*Usage*:`#RECORDUPLOAD {state}`  
  
# Users in the DB  
| id       |      name     |  first name |           email                   |   password   | role     |  
|----------|:-------------:|:-----------:|:---------------------------------:|:------------:|:--------:|  
| 3        |  SANSON       | Yvan       | yvan.sanson@etu.umontpellier.fr    |LearnTogether |STUDENT   |        
| 4        |    SALELLES   |   Marie    | marie.salelles@etu.umontpellier.fr |Test          |STUDENT   |  
| 6        | Teacher       |    PolyMtp | teacher@umontpellier.fr            |Teacher       |TEACHER   |  
| 8        | Staff         | PolyMtp    | StaffAdmin@umontpellier.fr         |AdmStaff      |ADMIN     |  
| 9        | SuperStaff    | PolyMtp    | SuperAdminAdmin@umontpellier.fr    |SuperAdmin    |SUPERADMIN|  
| 11       | Stratulat     | Tiberiu    | tibi.stratulat@umontpellier.fr     |Tibi          |TEACHER   |