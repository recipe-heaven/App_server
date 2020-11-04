FROM maven:3.6.3-adoptopenjdk-11 as builder
WORKDIR /build_dir
COPY src .
COPY pom.xml .
RUN mvn clean compile package
RUN mkdir /runable/

# If using shade
RUN rm -f target/original-*.war
RUN mv -f target/*.war /runable/
#

FROM open-liberty:kernel

#FROM openliberty/open-liberty:kernel-java11-openj9-ubi
RUN rm -rf /config/dropins/*
COPY --from=builder --chown=1001:0 /runable/*.war /config/dropins/

USER root
RUN mkdir /images
RUN chown 1001:1001 /images
RUN chmod 777 /images
USER 1001

#ENV WLP_LOGGING_MESSAGE_SOURCE=
ENV WLP_LOGGING_CONSOLE_LOGLEVEL=info
ENV WLP_LOGGING_CONSOLE_SOURCE=message,trace,accessLog,ffdc,audit

#COPY --chown=1001:0  src/main/liberty/config/postgresql-42.2.16.jar /config/
#COPY --chown=1001:0  target/recipe-heaven.war /config/dropins/
COPY --chown=1001:0  src/main/liberty/config/server.xml /config

RUN configure.sh