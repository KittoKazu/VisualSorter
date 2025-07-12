import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.openjfx.javafxplugin") version "0.0.14"
    kotlin("jvm") version "2.1.21"
    application
}

group = "nl.kittokazu"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.openjfx:javafx-controls:21.0.2")
    implementation("org.openjfx:javafx-fxml:21.0.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "21"
}

javafx {
    version = "21"
    modules = listOf("javafx.controls")
}

application {
    mainClass.set("nl.kittokazu.MainKt")
}