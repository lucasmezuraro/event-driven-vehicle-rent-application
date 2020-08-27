FROM adoptopenjdk/openjdk14
COPY ./preparation-service-application/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]