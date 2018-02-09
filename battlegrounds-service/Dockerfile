FROM openjdk:8u151
ADD ./build/libs/battlegrounds-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
EXPOSE 8094
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
