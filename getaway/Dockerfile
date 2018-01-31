FROM openjdk:8u151
ADD ./build/libs/getaway-service-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8080
CMD cp /tmp/getaway-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
