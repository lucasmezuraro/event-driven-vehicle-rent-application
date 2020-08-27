FROM adoptopenjdk/openjdk14
COPY ./hystrixdashboard/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]