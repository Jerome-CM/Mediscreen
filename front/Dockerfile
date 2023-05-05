FROM openjdk:11
EXPOSE 9000
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} front.jar
ENTRYPOINT ["java","-jar","/front.jar"]