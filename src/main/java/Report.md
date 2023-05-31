## 8th assignment
# STEAM

- In this assignment we used socket-programing and database to create simple sample of STEAM
- I created a databse called "steam" and  tables for it :one for accounts, one for video games and another one for the games that has been downloaded

### socket (network) programing
- first I wrote PrintStream to send data to the other sid, BufferReader to read data from the other side and keyboard and ObjectInputStream and ObjectInputStream to send and receive object.
- I made a socket to make the connection between client and server
 

### Database
- for the database I used **postgreSQL** and made the tables in **pgadmin4**
- I fist had problem connecting my database to the intellij because the JDBS diver wouldn't installed correctly
- in my database which is called *steam* I created 3 table for account, game and download with given columns in the README.
- every part of the program that I need data form database(in server side) , I made a connection with URL, username and password; write the needed query and create a statement
- and after sending that data to the client side, I closed them(statement and connection).
 
### main function
- I wrote a getMessage function to get the command from the clinet and do what is needed, for example at first it asked the user if they have an account or dont
- if they already have an account they have to write their username and password 
- the username and password will be checked in database (table account) from function *LoginDo* ,and if username and password exists they can go further
- and if they dont have one, the program first get a username, password and their date of birth and create an account for them and add it to the account table
- i put the column "username" as a primary key to prevent duplicated usernames

### what user can do
- after log in or sign up user can do different things like browse the available video games, see detail of a specific video game or download one
- if they choose the browse choice, the server will go to the browse() function
- in this function we will get the column **title** of table *games* from database and show it to the user
- if they want to see the detail of a video gam, first the server asks them to write name of that video game and server pass that name to the *showDetail* 
- this function will go to the database, table *game* and get title, developer, genre, price, release_year and size columns of the table and show it to the user
- and if user want to download a video game, again we fist get the name of that video game then go to the *findPNGPath()* function and get the column **file_path** of the table game and copy that to the download, and everytime user download a video game we add 1 to the download cont


### backUp
- I get a backup of my database from pgdmin 
- i clicked the option *backup* and put it's name "steam_backup" and I store it in folder backup


### Request and Response
- in some part of the program when we need to send data from client to server and reverse, I used Request and Response classes
- for example: when a user want to log in to the program I send its username and password with class RequestLogin which extends Request class
- and then I made an object of *RequestLogin* class with username and password that I get form the user and then send it as an object to the server through socket (with ObjectInputStream and ObjectInputStream which I created in both sides)


### Import from resource
- to import the data in resource and put them in table *game* in database I wrote a class named **ImportResources**
- in this class i first made a connection between the program and database with getConnection(and of course the url, username and password) and then get the data of TXTs and store them in table
- this class need to be run only once and it will store all that video games' details on the specific rows
