FROM openjdk:8u151
ADD ./build/libs/dataprovider-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
EXPOSE 8093
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
