plugins {
    kotlin("jvm") version "2.0.0"
    jacoco
    application
    id("io.gitlab.arturbosch.detekt") version "1.23.5"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("com.gradleup.shadow") version "8.3.1"
}

group = "com.appsoluut"
version = "0.0.1"

application {
    mainClass.set("com.appsoluut.connect4.Connect4")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter-params")
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.appsoluut.connect4.Connect4"
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

detekt {
    config.setFrom(files("detekt.yml"))
    buildUponDefaultConfig = true
}

ktlint {
    verbose.set(true)
    filter {
        exclude("**/build/**")
        include("**/kotlin/**")
    }
}
