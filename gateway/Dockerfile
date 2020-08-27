FROM adoptopenjdk/openjdk14
COPY ./gateway/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]