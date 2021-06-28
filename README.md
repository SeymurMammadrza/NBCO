The application aims to handle requirements of Non-Bank Credit Organization. Users from organization can view , edit, delete customer properties containing Loan and payment information.
To run the application , MySQL database and Intellij IDEA are required. After cloning into local folder of your computer, database has to be created.
It can be done via mysql-client by running "create database nbco;" script. 
After creation of database, application should be run via Intellij Idea Platform. Once it's built, please get the content of "script_to_initiate.sql" file. Script should be run via MySQL. This script will create a new admin to login. 

default ADMIN user : admin

default ADMIN password : test1234

http://localhost:8888/login

By creating user account, you can access the whole system. You have to log out and re-login to the app.

Implemented technologies : Spring Security , Thymeleaf , Maven , MySQL , Hibernate.
