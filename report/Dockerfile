FROM openjdk:11
EXPOSE 9003
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} report.jar
ENTRYPOINT ["java","-jar","/report.jar"]