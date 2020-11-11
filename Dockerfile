FROM openliberty/open-liberty:kernel-java11-openj9-ubi

# SET LOGGING LEVELS
ENV WLP_LOGGING_CONSOLE_LOGLEVEL=info
ENV WLP_LOGGING_CONSOLE_SOURCE=message,trace,accessLog,ffdc,audit

USER root
RUN mkdir /images
RUN chown 1001:1001 /images
RUN chmod 777 /images
USER 1001

# COPY REQUIRED FILES FOR CONTAINER
COPY --chown=1001:0  src/main/liberty/config/postgresql-42.2.16.jar /config/
COPY --chown=1001:0  target/recipe-heaven.war /config/dropins/
COPY --chown=1001:0  server.xml /config

RUN configure.sh