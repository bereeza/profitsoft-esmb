FROM openjdk:17-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 1003
ENTRYPOINT ["java", "-jar", "app.jar"]
