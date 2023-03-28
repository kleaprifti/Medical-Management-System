# Medical-Management-System
This application is a used to facilitate the process in a hospital for a hospital receptionist. This is useful to have the list of appointments in a structural way. 
It is useful to make the connection between the doctors and patients.

**How to install the project**
First step: Is to run in Git Bash git clone https://github.com/kleaprifti/Medical-Management-System.git (the link is found in the green <>Code button


***Deploying into Amazon Web Services by following the steps:***
First step : Create a database in your remote server 
connect local database with the remote server by changing into the configuration the spring.datasource.url with the remote server aws database link.
Second step: My generating this command ##mvn clean install it will build .jar file. 
Third step: .jar file that is generated will be used to deploy the application in an environment with Elastic Beanstalk.


**Technologies**
Java 17 Oracle OpenJDK version 17.0.6
Framework Spring Boot
