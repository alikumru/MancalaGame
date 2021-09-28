FROM maven:3.6.0-jdk-11-slim AS  builder
WORKDIR /buildfolder
COPY . .
RUN mvn clean package

FROM openjdk:12-alpine

COPY --from=builder /buildfolder/target/ .
EXPOSE 8082
CMD ["java","-jar","mangala-game.jar"]
