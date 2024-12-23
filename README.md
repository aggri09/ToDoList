# TODO list

How to start the application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/Todo-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8000`
ususally it is 8080 but since the port has been set as 8000 in config.yml  
   

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
