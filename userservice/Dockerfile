FROM java:8
CMD pwd
CMD ls ./target
COPY /target/userservice-0.0.1-SNAPSHOT.jar userservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar" "-Dspring.profiles.active=mysql","userservice-0.0.1-SNAPSHOT.jar"]