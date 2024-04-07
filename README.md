# Chatop App

### Context
Implement the back-end of an Angular website. This is a seasonal rental application. 

### Technology
- Java 17
- Spring Boot
- IDE (IntelliJ)
- MySql Workbench (or a MySql database installed)
- Software (like Postman)
- Cloudinary (a cloud medias server)

## How to build
- Clone this repository in a local location on your machine.
- ?? Connect to MySql Workbench and import the database.sql file in the ChaTopApp folder.
- ?? Execute the SQL script to create the database and tables users, rental and message.
- Open the ChaTopApp project with IntelliJ.
- Modify the DB_USERNAME and DB_PASSWORD variables in the .env file. These are the variables that allow you to connect to MySql. 
- Check the other variables and adapt them to your environment if necessary.
- Run the application with IntelliJ.
You can now use a software like Postman or a front-end app (Angular, React etc...) to communicate with the back-end.

### API documentation
Documentation is available with swagger at http://127.0.0.1:3001/documentation and in json format at http://127.0.0.1:3001/documentation-api. 
Please note that the documentation is only available if the API is running.

### WARNING
The application is configured to update the database. 
If you want to delete once shut down, you have to change this line : 
`spring.jpa.hibernate.ddl-auto=update` to `spring.jpa.hibernate.ddl-auto=create-drop` 
in the application.properties file on ChaTopApp/src/main.ressources

### Author
Marushka LABORDE-DONSKOFF