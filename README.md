# byte.Me documentation

## Preparations

<br/>

1. Before you can start the application, you must create a local PostgreSQL database<br/><br/>
2. You have to configure the following enviromental variables:
    - DB_USERNAME (e.g.: postgres)
    - DB_PASSWORD (e.g.: postgres)
    - DB_NAME (e.g.: byteme)
    - JWT_SECRET (make sure it has enough entropy. It MUST have a size >= 256 bits)


## Run Prod

backend:
1. mvn clean package
2. java -DDB_USERNAME=postgres -DDB_PASSWORD=postgres -DDB_NAME=byteme -DJWT_SECRET=myverylongandverysecuredsecretkey -jar target/byteMe-0.0.1-SNAPSHOT.jar

frontend:
1. npm run build
2. serve -s build
