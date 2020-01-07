FROM openjdk:8-jre
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} /app.jar
EXPOSE 8081
ENTRYPOINT ["nohup", "java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar","--spring.profiles.active=test", "&"]

