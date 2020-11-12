FROM  payara/micro
#FROM payara/server-full:5.2020.4-jdk11

USER root
RUN mkdir /images
RUN chown payara:payara /images
RUN chmod 777 /images
RUN rm -rf  $DEPLOY_DIR/*
USER payara

COPY ./.built_war/ROOT.war $DEPLOY_DIR/

EXPOSE 8080

CMD ["--deploymentDir", "/opt/payara/deployments","--nocluster", "--contextroot", "my"]
