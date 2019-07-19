FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
RUN mkdir -p /usr/src/app
ARG JAR_FILE
ARG MONGO_CONFIG
ENV MONGO_PARAMS=${MONGO_CONFIG}
COPY ${JAR_FILE} /usr/src/app/app.jar
CMD java $MONGO_PARAMS -Djava.security.egd=file:/dev/./urandom -jar -Dserver.address=0.0.0.0 -Dserver.port=8080 /usr/src/app/app.jar