FROM openjdk:8-jre-alpine
WORKDIR /customers
ADD ./build/libs/customers-0.0.1-SNAPSHOT.jar /customers/customers-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD java -jar customers-0.0.1-SNAPSHOT.jar