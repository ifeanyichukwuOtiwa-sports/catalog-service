# syntax=docker/dockerfile:1

# #STAGE1
FROM eclipse-temurin:21 AS builder
WORKDIR /workspace

ARG JAR_FILE=build/libs/*.jar

COPY ${JAR_FILE} catalog-service.jar

RUN java -Djarmode=layertools -jar catalog-service.jar extract

# #STAGE 2

FROM eclipse-temurin:21

RUN useradd spring #security
USER spring

WORKDIR /workspace
COPY --from=builder /workspace/dependencies/ ./
COPY --from=builder /workspace/spring-boot-loader/ ./
COPY --from=builder /workspace/snapshot-dependencies/ ./
COPY --from=builder /workspace/application/ ./

RUN ls ./lib || true

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
