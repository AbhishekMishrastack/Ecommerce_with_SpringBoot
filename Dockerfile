
FROM openjdk:17-jdk-slim
ADD target/spring-demo.jar  spring-demo.jar
ENTRYPOINT ["java","-jar","/spring-demo.jar"]