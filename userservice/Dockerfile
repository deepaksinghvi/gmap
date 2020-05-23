FROM openjdk:8-jre-alpine
EXPOSE 8080
COPY target/userservice-0.0.1-SNAPSHOT.jar userservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Dspring.profiles.active=postgres","-jar","userservice-0.0.1-SNAPSHOT.jar"]