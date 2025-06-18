# 1) build stage
FROM eclipse-temurin:23-jdk AS builder
WORKDIR /workspace
COPY . .
# Gradle Wrapper:
RUN ./gradlew clean build -x test

# 2) runtime stage
FROM eclipse-temurin:23-jdk
WORKDIR /app
# копираме само готовия jar от build stage
COPY --from=builder /workspace/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]