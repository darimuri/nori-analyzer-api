FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.0.15_10-slim
ARG JAR_FILE=jars/*.jar

RUN addgroup --gid 1000 -S spring && \
    adduser --uid 1000 -G spring -S spring && \
    mkdir -p /home/spring/jars && \
    mkdir -p /home/spring/userdict

USER spring:spring
COPY ${JAR_FILE} /home/spring/jars/app.jar
COPY userdict/userdict.txt /home/spring/userdict/userdict.txt

WORKDIR /home/spring/
ENV NORI_USERDICT_PATH=/home/spring/userdict/userdict.txt

EXPOSE 8080
ENTRYPOINT ["java","-jar","jars/app.jar"]