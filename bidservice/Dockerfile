FROM openjdk:8-jre-alpine
EXPOSE 8081
COPY target/bidservice-0.0.1-SNAPSHOT.jar bidservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-Dspring.profiles.active=postgres","-jar","bidservice-0.0.1-SNAPSHOT.jar"]