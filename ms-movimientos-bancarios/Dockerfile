FROM maven:3-jdk-11-slim as BUILD

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN mvn -e -X --batch-mode -f pom.xml -s settings.xml clean package
RUN cp  /usr/src/app/target/*.jar /usr/src/app/target/app.jar


FROM adoptopenjdk/openjdk11-openj9:alpine-slim

ENV PORT 8080
EXPOSE 8080

RUN  apk update && apk upgrade
COPY --from=BUILD /usr/src/app/target/app.jar /opt/target/
#ADD \ms-movimientos-bancarios\0.0.1-SNAPSHOT\ms-movimientos-bancarios-0.0.1-SNAPSHOT.jar /opt/target/elastic-apm-agent.jar


WORKDIR /opt/target

# Crear usuario
RUN adduser -D dockeruser

# Asignar permisos
RUN chown -R dockeruser /opt/target/

# Se define el usuario a ejecutar
USER dockeruser

#Se agrega variables para carga de APM
#CMD ["/bin/sh", "-c", "java -javaagent:$ELASTIC_APM_AGENT_JAR -Delastic.apm.service_name=$ELASTIC_APM_SERVICE -Delastic.apm.environment=$ELASTIC_APM_ENV -Delastic.apm.application_packages=$ELASTIC_APM_APPLICATION_PACKAGES -Delastic.apm.server_urls=$ELASTIC_APM_SERVER_URLS -Delastic.apm.secret_token=$ELASTIC_APM_SECRET_TOKEN -Xshareclasses -Xquickstart -jar app.jar" ]

#Descomentar la linea de abajo para ejecutar sin apm
CMD ["/bin/sh", "-c", "java -Xshareclasses -Xquickstart -jar app.jar" ]