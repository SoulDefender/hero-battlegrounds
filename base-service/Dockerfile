FROM openjdk:8u151
ADD ./build/libs/base-service-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8090
CMD cp /tmp/base-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
