FROM openjdk:8u151
ADD ./build/libs/eureka-service-0.0.1-SNAPSHOT.jar /tmp
EXPOSE 8761
CMD cp /tmp/eureka-service-0.0.1-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT ["java","-jar","/tmp/app.jar"]
