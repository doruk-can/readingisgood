# Readingisgood

ReadingIsGood is an online books retail firm which operates only on the Internet. Main target of ReadingIsGood is to deliver books from its one centralized warehouse to their customers within the same day. That is why stock consistency is the first priority for their vision operations.


# Tech Stack

 * Java 11
 * Maven
 * Spring Boot 2.5.5
 * MongoDB 4.2.7
 * Lombok
 * Git
 * Springdoc-openapi (You can check it from http://localhost:8080/get-documentation)
 * Docker
 * Docker Compose
 
 
# Details

To use api's you should use registered users. Some apis cannot be used by ordinary users (such as addNewBook, getOrdersByDateInterval, changeOrderStatus), admins can use all apis. It is possible to register a new user via signup api.


# How to start

In terminal change direction to readingisgood folder -> cd ../readingisgood
Execute this command -> docker build -t springboot-mongodb:1.0 .
Execute this command -> cd src
Execute this command -> docker-compose up    or in detach mode if you don't want to see logs -> docker-compose up -d

Now our application is working. To use apis you should sign up. We have to add roles collection before using apis on postman. It's a necessary collection to allow new sign up request.

In terminal execute this command -> docker exec -it mymongodb bash
Then -> mongo
Then -> use readingisgood_db
Then -> db.roles.insert(
   [
     { name: "ROLE_ADMIN" },
     { name: "ROLE_USER" }
   ]
)


Now you can use the application. When you sign up request body's role attribute can be ["admin"] or ["user"] .











 
 

