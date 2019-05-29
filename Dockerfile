FROM gradle:5.4.1-jdk8-alpine as compile
LABEL MAINTAINER "misrahariom@gmail.com"
USER root
WORKDIR /opt/customer-api
ADD --chown=gradle:gradle . /opt/customer-api
RUN ls -lhtr /opt/customer-api
RUN gradle build --stacktrace
RUN ls -lhtr /opt/customer-api/build

FROM openjdk:8-jre-alpine as apps
RUN mkdir -p /apps
WORKDIR /apps
RUN cd /
COPY --from=compile /opt/customer-api/build/libs/customers-0.0.1-SNAPSHOT.jar /apps
EXPOSE 8083
ENTRYPOINT [ "java", "-jar", "/apps/customers-0.0.1-SNAPSHOT.jar"]