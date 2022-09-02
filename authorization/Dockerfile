FROM openjdk:11
EXPOSE 8008
ADD target/authorization-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]