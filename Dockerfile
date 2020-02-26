FROM openjdk:11-jre-slim
COPY ./build/libs/dropwizard-sample.jar dropwizard-sample.jar
COPY ./confg-docker.yml confg.yml
EXPOSE 8082
CMD java ${JAVA_OPTS} -jar dropwizard-sample.jar server confg.yml