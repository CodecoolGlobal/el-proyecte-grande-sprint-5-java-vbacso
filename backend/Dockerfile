FROM maven:3.8.7-openjdk-18-slim as builder

WORKDIR build

COPY pom.xml .

RUN mvn dependency:go-offline dependency:resolve-plugins

COPY src src

RUN mvn package

FROM openjdk:18-jdk-alpine3.15

WORKDIR /app

COPY --from=builder /build/target/byteMe-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar","-DDB_PASSWORD=postgres","-DDB_USERNAME=postgres","-DDB_NAME=byteme","-DJWT_SECRET=myverylongandverysecuresecretkey", "byteMe-0.0.1-SNAPSHOT.jar"]
