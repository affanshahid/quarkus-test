FROM quay.io/quarkus/centos-quarkus-maven:20.2.0-java11 AS build
COPY pom.xml /usr/src/app/
RUN mvn -f /usr/src/app/pom.xml -B de.qaware.maven:go-offline-maven-plugin:1.2.5:resolve-dependencies
COPY src /usr/src/app/src
USER root
RUN chown -R quarkus /usr/src/app
USER quarkus
RUN mvn -f /usr/src/app/pom.xml -Pnative -Dquarkus.native.additional-build-args=--static  clean package
RUN chmod 777 /usr/src/app/target/*-runner

## Stage 2 : create the docker final image
FROM alpine
WORKDIR /work/
COPY --from=build /usr/src/app/target/*-runner /work/application
CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]