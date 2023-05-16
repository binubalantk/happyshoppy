FROM openjdk:18-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service.jar
ENTRYPOINT ["java","-jar","/service.jar"]
