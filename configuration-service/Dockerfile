FROM openjdk:8u151
ADD ./build/libs/configuration-service-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 7666
CMD cp /tmp/configuration-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
