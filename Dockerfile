FROM openjdk:18-jdk-alpine3.15
COPY target/byteMe-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar","-DDB_PASSWORD=postgres","-DDB_USERNAME=postgres","-DDB_NAME=byteme","-DJWT_SECRET=myverylongandverysecuresecretkey", "byteMe-0.0.1-SNAPSHOT.jar"]