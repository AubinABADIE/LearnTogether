# Learn Together

This readme is used to provide a better understanding of the different commands and the communication protocol between the server and the clients.


# FROM the client

## \#LOGIN
Used when the client wants to connect to the server. With this command, the server checks the database to find the user's credentials and whether grand or not the access.
*Usage*: `#LOGIN {email} {password}`

## \#FIRSTPWD
Used when the client wants to change its password **for the first time** (he hasn't yet connected, and in the DB, the password is blank).
*Usage*: `#FIRSTCONN {email} {password}`

## \#CREATEROOM
Used when the client wants to create a room into the database. The parse method for the different parameters is "-/-"  
Arguments:
- name: String
- capacity: int
-building: int
- hasProjector: boolean
- hadComputer: boolean
- description: String

*Usage*: `#CRATEROOM-/-{name}-/-{capacity}-/-{building}-/-{hasProjector}-/-{hasComputer}-/-{description}`

## \#SENDMSGTOCLIENT
Used when the client wants to send a message to another user through the server. The parse method for the different parameters is "-/-"  
Arguments:
- id: the sender's id.
- email: the receiver's email. 
- message: the message sent.
 *Usage*: `#LOGIN {id} {email} {message}`

# FROM the server


## \#LOGON 

The server responds to a #LOGIN injonction from the client by sending this to the client. It is accompanied by several arguments:

 - isConnected: boolean, true if the connection has been successful or false otherwise
 - id: int the user id from the DB
 - role: String the user role in the application.
*Usage*:`#LOGON {isConnected} {id} {role}`

## \#FIRSTCONN
The server responds to a #FIRSTCONN injonction from the client by sending this.
Arguments :
- hasSucceeded : boolean, true if it was the first connection and the password has been defined, false otherwise.

*Usage*:`#FIRSTCONN {hasConected}`

## \#CREATEDROOM
The server responds to a #CREATEROOMinjonction from the client by sending this.
Arguments :
- mess : String with the result of the action.

*Usage*: `#CREATEDROOM {mess}`

## \#MESSAGE
The server responds to a #SENDMSGTOCLIENT demand with this. It has two states, either sent (the message is in the DB) or error (the message cannot be put into the DB)  
*Usage*:`#MESSAGE {State}`

#Users in the DB
| id       |      name     |  first name |           email                   |   password   | role     |
|----------|:-------------:|:-----------:|:---------------------------------:|:------------:|:--------:|
| 3        |  SANSON       | Yvan       | yvan.sanson@etu.umontpellier.fr    |LearnTogether |STUDENT   |      
| 4        |    SALELLES   |   Marie    | marie.salelles@etu.umontpellier.fr |Test          |STUDENT   |
| 6        | Teacher       |    PolyMtp | teacher@umontpellier.fr            |Teacher       |TEACHER   |
| 8        | Staff         | PolyMtp    | StaffAdmin@umontpellier.fr         |AdmStaff      |ADMIN     |
| 9        | SuperStaff    | PolyMtp    | SuperAdminAdmin@umontpellier.fr    |SuperAdmin    |SUPERADMIN|
    