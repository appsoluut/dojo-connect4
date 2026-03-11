# Connect4

![Build](https://github.com/appsoluut/dojo-connect4/actions/workflows/ci.yml/badge.svg) [![codecov](https://codecov.io/gh/appsoluut/dojo-connect4/branch/main/graph/badge.svg)](https://codecov.io/gh/appsoluut/dojo-connect4) [![DeepSource](https://app.deepsource.com/gh/appsoluut/dojo-connect4.svg/?label=code+coverage&show_trend=true&token=UmCazOJ6A2-UkVOMKafStlYo)](https://app.deepsource.com/gh/appsoluut/dojo-connect4/) [![DeepSource](https://app.deepsource.com/gh/appsoluut/dojo-connect4.svg/?label=active+issues&show_trend=true&token=UmCazOJ6A2-UkVOMKafStlYo)](https://app.deepsource.com/gh/appsoluut/dojo-connect4/) [![DeepSource](https://app.deepsource.com/gh/appsoluut/dojo-connect4.svg/?label=resolved+issues&show_trend=true&token=UmCazOJ6A2-UkVOMKafStlYo)](https://app.deepsource.com/gh/appsoluut/dojo-connect4/)

A Kotlin project bootstrapped with **Gradle**, **Detekt**, **Ktlint**, **JUnit**, and **JaCoCo**.

Running the immensely popular 4-in-a-row game!

## 🚀 Features

- 🔧 Gradle wrapper included
- 🧹 Ktlint & Detekt code quality checks
- 🧪 Unit testing with JUnit
- 📊 Code coverage via JaCoCo
- 🤖 CI/CD with GitHub Actions
- ☁️ Codecov integration
- ☁️ DeepSource integration

## 🧰 Setup

```bash
# Clone and enter project
git clone https://github.com/appsoluut/dojo-connect4.git
cd Connect4

# Build and test
./gradlew build test

# Build fat jar
./gradlew shadowJar

# Build distribution
./gradlew shadowDistZip

# Run Detekt and Ktlint checks
./gradlew detekt ktlintCheck

# Run
./gradlew run
```

# Git hook

The GitHub actions will validate if the Ktlint Format is used. To make sure that you don't accidentally push
a commit which would fail this quick check, you can register a Git hook by running the following Gradle command:

```bash
./gradlew :addKtLintCheckGitPreCommitHook
```

Commits will fail now if the Ktlint Checks don't pass first.

# Docker

There is a Docker image available. You can run it in interactive mode (to play the game) with the following command:

```bash
docker pull ghcr.io/appsoluut/dojo-connect4:latest
docker run --rm -it ghcr.io/appsoluut/dojo-connect4:latest
```

The game also logs the game history to `./logs/history.log`. If you wish to persist the file, use the `-v` option of
Docker. For example:

```bash
# Store the logs under the current working directory /logs
docker run -it -v "$(pwd)/logs":/app/logs ghcr.io/appsoluut/dojo-connect4:latest
```

**Note:** Make sure the logs directory is created on your host and writeable by Docker

To build your own image, simply run `docker build -t <your-image>`.

To run that container, run `docker run it <your-image>`.

# License

MIT - feel free to use and modify.
