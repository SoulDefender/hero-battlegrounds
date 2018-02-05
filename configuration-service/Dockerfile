FROM openjdk:8u151
ADD ./build/libs/configuration-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
EXPOSE 7666
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
