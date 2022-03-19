# AWS Mock

## Deployment

##Maven

Build:
```
mvn install
```
and deploy:
```
mvn spring-boot:run
```

##OR Java JAR

Build the jar:
```
./mvnw clean verify
```
then run:
```
java -jar target/inf-0.0.1-SNAPSHOT.jar
```

Authentication is performed with credentials admin/123456 on endpoint:

```
/api/authenticate
```
