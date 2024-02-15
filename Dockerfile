FROM maven:3.9.0-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn clean install

FROM eclipse-temurin:17.0.6_10-jdk
WORKDIR /app
COPY --from=build /app/target/your-project-1.0-SNAPSHOT.jar /app/

CMD ["java", "-jar", "your-project-1.0-SNAPSHOT.jar"]


