FROM openjdk:8u151
ADD ./build/libs/dataprovider-service-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8093
CMD cp /tmp/dataprovider-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
