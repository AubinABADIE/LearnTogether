
# Learn Together  Read Me


## Github link :

[https://github.com/AubinABADIE/LearnTogether]


# Launch instructions 
## If without the JARs:
### Step 1 
Download the application source code. If you have an IDE you can import a new project with the learn together src. 
### Step 2 
When the project is imported in the IDE, you must to install the libraries. All the libraries are in the lib folder. 

 - If the IDE is Eclipse : 
Right click on project then click on build path , configure build path and add external Jars. 
 - If the IDE is IntelliJ : 
 Click on file, then project structure, libraries and choose the libraries. 
### Step 3
Launch the server console to start the application server. 
### Step 4 
Launch the startUI to connect a client.
   
##If you have the JARs available:
For the client side application, nothing particular is required. Just run the application by double-clicking on the JAR file.
For the server side:
- In a terminal at the location of the JAR file, enter the command `jar xf LearnTogetherServer.jar`
- This will unarchive the different classes contained in the JAR file.
- To launch the server, type `java server.ServerConsole` at the same location as before.

## Remarks 
To see the different user interfaces according to the different user types you have to connect with different login. 
There are 4 interfaces: 
 - The student interface, 
 - The teacher interface,
 - The administrator interface,
 - The super administrator interface. 
You cannot create a user at this time (unless you are connected as an administrator), so you must use the already created users in the data base. You can find them at the end of this document.
If you create a user with the administrator or super administrator account you can connect to this new account  but you have to click on the *first connection* button to configure the user's password. Finally, you are redirected to the connection page and you can enter your login and password to connect.  
For some reason, the WI-FI network *eduroam* blocks the connection to the database (and Azure in general). Please connect to another WI-FI if possible, or a VPN to bypass this limitation.

# Launch tests
To launch tests, type `java test.UserServicesTest` in a terminal.

# Protocol 
  
This part is used to provide a better understanding of the different commands and the communication protocol between the server and the clients.  
  
  
## FROM the client  
  
### #LOGIN  
Used when the client wants to connect to the server. With this command, the server checks the database to find the user's credentials and whether grand or not the access.  
*Usage*: `#LOGIN {email} {password}`  
  
### #FIRSTPWD  
Used when the client wants to change its password **for the first time** (he hasn't yet connected, and in the DB, the password is blank).  
*Usage*: `#FIRSTCONN {email} {password}`  
  
### #CREATEROOM  
Used when the client wants to create a room into the database. The parse method for the different parameters is "-/-"    
Arguments:  
- name: String  
- capacity: int  
- building: int  
- hasProjector: boolean  
- hadComputer: boolean  
- description: String  
  
*Usage*: `#CREATEROOM-/-{name}-/-{capacity}-/-{building}-/-{hasProjector}-/-{hasComputer}-/-{description}`  
  
### #DELETEROOM  
Used when the client wants to delete a room. The parse method for the different parameters is "-/-"  
Arguments:  
- id : int  
  
*Usage*: `#DELETEROOM-/-{id}`  
  
### #UPDATEROOM  
Used when the client wants to update a room into the database. The parse method for the different parameters is "-/-"  
Arguments:  
- id : int  
- name: String  
- capacity: int  
- building: int  
- hasProjector: boolean  
- hadComputer: boolean  
- description: String  
  
*Usage* : `#UPDATEROOM-/-{id}-/-{name}-/-{capacity}-/-{building}-/-{hasProjector}-/-{hasComputer}-/-{description}`  
  
### #GETROOMS  
Used when the client wants the list of all rooms.  
No parameters.  
  
### #CREATECOURSE
Used when the client wants to create a course into the database. The parse method for the different parameters is "-/-"    
Arguments:  
- name: String  
- description : String
- nbTotalHour : int
- idT : int  

  
*Usage*: `#CREATECOURSE-/-{name}-/-{description}-/-{nbTotalHour}-/-{idT}`  
  
### #DELETECOURSE  
Used when the client wants to delete a course. The parse method for the different parameters is "-/-"  
Arguments:  
- id : int  
  
*Usage*: `#DELETECOURSE-/-{id}`  
  
### #UPDATECOURSE
Used when the client wants to update a course into the database. The parse method for the different parameters is "-/-"  
Arguments:  
- name: String  
- description : String
- nbTotalHour : int
- idT : int  
  
*Usage* : `#UPDATECOURSE--/-{name}-/-{description}-/-{nbTotalHour}-/-{idT}`  
  
### #GETCOURSES  
Used when the client wants the list of all courses.  
No parameters.  

### ##GETCOURSET
Used when the client want the list of his own courses.

*Usage* : `#GETCOURSET--/-{userID}-
  
  
### #SENDMSGTOCLIENT  
Used when the client wants to send a message to another user through the server. The parse method for the different parameters is "-/-"    
Arguments:  
- id: the sender's id.  
- email: the receiver's email.   
- message: the message sent.  
 *Usage*: `#LOGIN {id} {email} {message}`  
   
 ### #RETRIEVECONVERSATION  
 Used when the client wants to retrieve all of the conversation between him and another client.    
 Arguments:  
 - id: int, the client id  
 - email: String, the other person's email.  
 *Usage*: `#RETRIEVECONVERSATION {id} {email}`  
   
 ### #GETCONVEMAIL  
 Used when the client wants to get all its discussions.  
 Arguments:  
 - id: int the asking ID    
 *Usage*:`#GETCONVEMAIL {id}`  
   
 ### #DELETECONVERSATION  
 The client asks to delete a conversation with this command.  
 Arguments:   
 - id: int, the asking ID  
 - email: String, the other email.    
 *Usage*: `#DELETECONVERSATION {id} {email}`  
  
 ### #GETRECORDS  
 The client send this to the server to receive the record list.  
 No arguments.  
 *Usage*: `#GETRECORDS`  
  
 ### #DOWNLOADRECORD  
 The client want to retrieve a record.  
 Argument :  
   - id: int, the record id  
   *Usage*: `#DOWNLOADRECORD {id}`

 ### #GETRECORDBYUSER
 The client want to retrieve all the record donating by a user.
 Argument :
 - id : int the user id
 *Usage*: `#GETRECORDBYUSER-/-{id}`

 ### #DELETERECORD
 The client want to delete a record.
 Argument :
 - id : int the record id
 *Usage*: `#DELETERECORD-/-{id}`
 
### ##GETTEACHER
The client want to recover all the teachers 

### #CREATEUSER
Used when the client wants to create a user into the database. The parse method for the different parameters is " "    
Arguments:  
- name: String  
- firstName : String
- email : String
- birthDate : String
- role : String
- password : String

*Usage*: `#CREATEUSER {name} {firstName} {email} {birthDate} {role} {password}`  
  
### #DELETEUSER
Used when the client wants to delete a user. The parse method for the different parameters is " "  
Arguments:  
- id : int  
- role : String
  
*Usage*: `#DELETEUSER {id} {role}`  
  
### #UPDATEUSER
Used when the client wants to update a user into the database. The parse method for the different parameters is " "  
Arguments:  
- name: String  
- firstName : String
- email : String
- birthDate : String
- role : String
  
*Usage* : `#UPDATEUSER {name} {firstName} {email} {birthDate} {role}`  

### #UPDATEADMINUSER
Used when the client wants to update a user, who is not an admin, to put him as an admin into the database. The parse method for the different parameters is " "
Arguments:
- name: String
- firstName : String
- email : String
- birthDate : String
- role : String
- isAdmin : int
*Usage* : `#UPDATEADMINUSER {name} {firstName} {email} {birthDate} {role} {isAdmin}`

### #UPDATEPWD
Used when the client wants to update his password into the database. The parse method for the different parameters is " "  
Arguments:  
- name: String  
- firstName : String
- email : String
- birthDate : String
- role : String
- password : String
  
*Usage* : `#UPDATEUSER {name} {firstName} {email} {birthDate} {role} {password}`  
  
### #GETUSERS 
Used when the client wants the list of all users into database.  
No parameters.  

### #GETUSER 
Used when the client wants a user into database to display his profile.  
No parameters.

### #GETADMIN
Used when the client wants the list of all admin users into database.
No parameters.

### #GETSTAFFNA
Used when the client wants the list of all no-admin staffs into database.
No parameters.

### #GETTEACHERNA
Used when the client wants the list of all no-admin teachers into database.
No parameters.

### #CREATEDEP
Used when the client wants to create a department into the database. The parse method for the different parameters is "-/-"
Arguments:
- name: String
- refTeacherId : int
- description : String
*Usage*: `#CREATEDEP-/-{name}-/-{refTeacherId}-/-{description}`

### #DELETEDEP
Used when the client wants to delete a department. The parse method for the different parameters is "-/-"
Arguments:
- id : int
*Usage*: `#DELETEDEP-/-{id}`

### #UPDATEDEP
Used when the client wants to update a department into the database. The parse method for the different parameters is "-/-"
Arguments:
- id = int
- name: String
- refTeacherId : int
- description : String
*Usage*: `#UPDATEDEP-/-{id}-/-{name}-/-{refTeacherId}-/-{description}`

### #GETDEPARTMENT
Used when the client wants the list of all departments.
No parameters.

### #CREATEPROMOTION
Used when the client wants to create a promotion into the database. The parse method for the different parameters is "-/-"
Arguments:
- name: String
- description : String
- graduationYear : int
- idDepartment : int
*Usage*: `#CREATEPROMOTION-/-{name}-/-{description}-/-{graduationYear}-/-{idDepartment}`

### #DELETEPROMOTION
Used when the client wants to delete a promotion. The parse method for the different parameters is "-/-"
Arguments:
- id : int
*Usage*: `#DELETEPROMOTION-/-{id}`

### #UPDATEPROMOTION
Used when the client wants to update a promotion into the database. The parse method for the different parameters is "-/-"
Arguments:
- id = int
- name: String
- description : String
- graduationYear : int
- idDepartment : int
*Usage*: `#UPDATEPROMOTION-/-{id}-/-{name}-/-{description}-/-{graduationYear}-/-{idDepartment}`

### #GETPROMOTION
Used when the client wants the list of all promotions.
No parameters.

### #GETPROMOTIONBYDEP
Used when the client wants the list of all promotions related to a specific department.
Arguments:
- idDepartment : int
*Usage*: `#GETPROMOTIONBYDEP-/-{idDep}`

### #CREATECLASS
Used when the client wants to create a class into the database. The parse method for the different parameters is "-/-"
Arguments:
- name: String
- description : String
- refPromoId : int
*Usage*: `#CREATECLASS-/-{name}-/-{description}-/-{refPromoId}`

### #DELETECLASS
Used when the client wants to delete a class. The parse method for the different parameters is "-/-"
Arguments:
- id : int
*Usage*: `#DELETECLASS-/-{id}`

### #UPDATECLASS
Used when the client wants to update a class into the database. The parse method for the different parameters is "-/-"
Arguments:
- id = int
- name: String
- description : String
- refPromoId : int
*Usage*: `#UPDATECLASS-/-{id}-/-{name}-/-{description}-/-{refPromoId}`

### #GETCLASS
Used when the client wants the list of all classes.
No parameters.

### #GETCLASSBYPROMO
Used when the client wants the list of all classes related to a specific promotion.
Arguments:
- idPromotion : int
*Usage*: `#GETCLASSBYPROMO/-{idPromotion}`



## FROM the server  
  
  
### \#LOGON   
  
The server responds to a #LOGIN injunction from the client by sending this to the client. It is accompanied by several arguments:  
  
 - isConnected: boolean, true if the connection has been successful or false otherwise  
 - id: int the user id from the DB  
 - role: String the user role in the application.  
*Usage*:`#LOGON {isConnected} {id} {role}`  
  
### \#FIRSTCONN  
The server responds to a #FIRSTCONN injunction from the client by sending this.  
Arguments :  
- hasSucceeded : boolean, true if it was the first connection and the password has been defined, false otherwise.  
  
*Usage*:`#FIRSTCONN {hasConected}`  
  
### \#CREATEDROOM  
The server responds to a #CREATEROOMinjonction from the client by sending this.  
Arguments :  
- mess : String with the result of the action.  
  
*Usage*: `#CREATEDROOM {mess}`  
  
 ### #DELETEROOM  
 The server responds to a #DELETEROOM from the client by sending this.  
 Arguments:  
 - mess : String with the result of the action.  
  
 *Usage*:  `#DELETEROOM {mess}` 
 
 ### \#CREATEDCOURSE
The server responds to a #CREATECOURSE injunction from the client by sending this.  
Arguments :  
- mess : String with the result of the action.  
  
*Usage*: `#CREATEDCOURSE {mess}`  
  
 ### #DELETECOURSE
 The server responds to a #DELETECOURSE from the client by sending this.  
 Arguments:  
 - mess : String with the result of the action.  
  
 *Usage*:  `#DELETECOURSE {mess}`  
  
### \#MESSAGE  
The server responds to a #SENDMSGTOCLIENT demand with this. It has two states, either sent (the message is in the DB) or error (the message cannot be put into the DB)    
*Usage*:`#MESSAGE {State}`  
  
### \#DELETEDCONVERSATION  
The server responds to a #DELETECONVERSATION demand with this. it has two states, either success or failure.  
*Usage*:`#DELETEDCONVERSATION {state}`  
  
### #RECORDUPLOAD  
When the server receives a RecordType to store in the database and storage service, it responds with this message. The message is either SUCCESS or FAILURE.    
*Usage*:`#RECORDUPLOAD {state}`  

### \#CREATEDUSER
The server responds to a #CREATEUSER from the client by sending this.  
Arguments :  
- mess : String with the result of the action.  
  
*Usage*: `#CREATEDUSER {mess}`  

### #UPDATEDUSER
The server responds to a #UPDATEUSER from the client by sending this.  
Arguments:  
- mess : String with the result of the action.  
  
*Usage*:  `#UPDATEDUSER {mess}` 

### #UPDATEDPWD
The server responds to a #UPDATEPWD from the client by sending this.  
Arguments:  
- mess : String with the result of the action.  
  
*Usage*:  `#UPDATEDPWD {mess}` 

### #DELETEDUSER
The server responds to a #DELETEUSER from the client by sending this.  
Arguments:  
- mess : String with the result of the action.  
  
*Usage*:  `#DELETEDUSER {mess}`


### \#CREATEDDEPARTMENT
The server responds to a #CREATEDEPARTMENT injunction from the client by sending this.
Arguments :
- mess : String with the result of the action.
*Usage*: `#CREATEDDEPARTMENT {mess}`

### #DELETEDDEPARTMENT
The server responds to a #DELETEDEPARTMENT from the client by sending this.
Arguments:
- mess : String with the result of the action.
*Usage*:  `#DELETEDDEPARTMENT {mess}`

### #UPDATEDDEPARTMENT
The server responds to a #UPDATEDEPARTMENT from the client by sending this.
Arguments:
- mess : String with the result of the action.
*Usage*:  `#UPDATEDDEPARTMENT {mess}`

### \#CREATEDPROMO
The server responds to a #CREATEPROMO injunction from the client by sending this.
Arguments :
- mess : String with the result of the action.
*Usage*: `#CREATEDPROMO {mess}`

### #DELETEDPROMO
The server responds to a #DELETEPROMOTION from the client by sending this.
Arguments:
- mess : String with the result of the action.
*Usage*:  `#DELETEDPROMO {mess}`

### #UPDATEDPROMO
The server responds to a #UPDATEPROMOTION from the client by sending this.
Arguments:
- mess : String with the result of the action.
*Usage*:  `#UPDATEDPROMOTION {mess}`


### \#CREATEDCLASS
The server responds to a #CREATECLASS injunction from the client by sending this.
Arguments :
- mess : String with the result of the action.
*Usage*: `#CREATEDCLASS {mess}`

### #DELETEDCLASS
The server responds to a #DELETECLASS from the client by sending this.
Arguments:
- mess : String with the result of the action.
*Usage*:  `#DELETEDCLASS {mess}`

### #UPDATEDCLASS
The server responds to a #UPDATECLASS from the client by sending this.
Arguments:
- mess : String with the result of the action.
*Usage*:  `#UPDATEDCLASS {mess}`

 
  
#Users in the DB  
| id       |      name     |  first name |           email                   |   password   | role     |  
|----------|:-------------:|:-----------:|:---------------------------------:|:------------:|:--------:|  
| 3        |  SANSON       | Yvan       | yvan.sanson@etu.umontpellier.fr    |LearnTogether |STUDENT   |        
| 4        |    SALELLES   |   Marie    | marie.salelles@etu.umontpellier.fr |Test          |STUDENT   |  
| 6        | Teacher       |    PolyMtp | teacher@umontpellier.fr            |Teacher       |TEACHER   |  
| 8        | Staff         | PolyMtp    | StaffAdmin@umontpellier.fr         |AdmStaff      |ADMIN     |  
| 9        | SuperStaff    | PolyMtp    | SuperAdminAdmin@umontpellier.fr    |SuperAdmin    |SUPERADMIN|  
| 11       | Stratulat     | Tiberiu    | tibi.stratulat@umontpellier.fr     |Tibi          |TEACHER   |
| 21       | VALDURIEZ     | Esther     | esther@umontpellier.fr             |null          |TEACHER   |

