# Optimal layer ordering for Kotlin builds
FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /build

# Layer 1: Gradle wrapper (almost never changes)
COPY gradlew ./
COPY gradle ./gradle

# Layer 2: Build configuration (changes occasionally)
COPY detekt.yml build.gradle.kts settings.gradle.kts ./

# Layer 5: Install dependencies
RUN ./gradlew dependencies

# Layer 4: Source code (changes frequently)
COPY src ./src

# Layer 5: Test & Build
RUN ./gradlew check shadowDistZip

RUN apt-get update -y \
    && apt-get -y upgrade \
    && apt-get install unzip -y \
    && unzip ./build/distributions/Connect4-*.zip -d dist \
    && mv dist/Connect4-*/* dist/ \
    && rm -rf dist/Connect4-*/

## Connect 4 resulting image
FROM eclipse-temurin:17-jre-alpine

# Attach to GitHub repository for better traceability
LABEL org.opencontainers.image.source=https://github.com/appsoluut/dojo-connect4
LABEL authors="appsoluut"

USER games

WORKDIR /app

COPY --from=build /build/dist/. .

ENTRYPOINT ["./bin/Connect4"]
