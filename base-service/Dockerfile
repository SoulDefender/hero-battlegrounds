FROM openjdk:8u151
ADD ./build/libs/base-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
