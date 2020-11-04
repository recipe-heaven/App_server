FROM maven:3.6.3-adoptopenjdk-11

USER root
RUN mkdir /images
RUN chown 1001:1001 /images
RUN chmod 777 /images
#USER 1001
ENV WLP_LOGGING_CONSOLE_LOGLEVEL=info
ENV WLP_LOGGING_CONSOLE_SOURCE=message,trace,accessLog,ffdc,audit

VOLUME /build_dir
WORKDIR /build_dir
CMD mvn liberty:dev
