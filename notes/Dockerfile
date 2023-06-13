FROM openjdk:11
EXPOSE 9002
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} notes.jar
ENTRYPOINT ["java","-jar","/notes.jar"]