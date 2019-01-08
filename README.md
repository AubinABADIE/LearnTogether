# Learn Together

This readme is used to provide a better understanding of the different commands and the communication protocol between the server and the clients.


# FROM the client

## \#LOGIN
Used when the client wants to connect to the server. With this command, the server checks the database to find the user's credentials and whether grand or not the access.
*Usage*: `#LOGIN {email} {password}`

## \#FIRSTPWD
Used when the client wants to change its password **for the first time** (he hans't yet connected, and in the DB, the password is blank).
*Usage*: `#FIRSTCONN {email} {password}`

# FROM the server


## \#LOGON 

The server responds to a #LOGIN injonction from the client by sending this to the client. It is accompanied by several arguments:

 - isConnected: boolean, true if the connection has been succesful or false otherwise
 - id: the user id from the DB
 - role: the user role in the application.
*Usage*:`#LOGON {isConnected} {id} {role}`

## \#FIRSTCONN
The server responds to a #FIRSTCONN injonction from the client by sending this.
Arguments :
- hasSucceeded : boolean, true if it was the first connection and the password has been defined, false otherwise.

*Usage*:`#FIRSTCONN {hasConected}`