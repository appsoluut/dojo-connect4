# Optimal layer ordering for Kotlin builds
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /build

COPY gradlew ./
COPY gradle ./gradle
COPY detekt.yml build.gradle.kts settings.gradle.kts ./

RUN ./gradlew dependencies

COPY src ./src

RUN ./gradlew check shadowDistZip

RUN apt-get update -y \
    && apt-get install unzip -y \
    && unzip ./build/distributions/Connect4-*.zip -d dist \
    && mv dist/Connect4-*/* dist/ \
    && rm -rf dist/Connect4-*/

## Connect 4 resulting image
FROM eclipse-temurin:17-jre-jammy

# Attach to GitHub repository for better traceability
LABEL org.opencontainers.image.source=https://github.com/appsoluut/dojo-connect4
LABEL authors="appsoluut"

RUN apk upgrade

USER games

WORKDIR /app

COPY --from=build /build/dist/. .

ENTRYPOINT ["./bin/Connect4"]
