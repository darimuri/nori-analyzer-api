FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.12_7-slim
ARG JAR_FILE=jars/*.jar

RUN addgroup --gid 1000 -S spring && adduser --uid 1000 -S spring -G spring
USER spring:spring
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]