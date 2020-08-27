FROM adoptopenjdk/openjdk14
COPY ./reserve-service-application/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]