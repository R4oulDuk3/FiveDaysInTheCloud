#FROM openjdk:19-jdk-oracle
#
#COPY target/FiveDaysInCloud-0.0.1-SNAPSHOT.jar FiveDaysInCloud-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/FiveDaysInCloud-0.0.1-SNAPSHOT.jar"]

FROM openjdk:19-jdk-alpine

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN dos2unix mvnw
RUN ./mvnw dependency:go-offline

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]