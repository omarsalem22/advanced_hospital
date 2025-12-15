FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY target/*SNAPSHOT.jar app.jar

RUN addgroup --system spring && adduser --system --group spring
USER spring

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/app.jar"]