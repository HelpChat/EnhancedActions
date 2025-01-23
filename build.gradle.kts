plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "at.helpch"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.helpch.at/releases")
}

dependencies {
    compileOnly(libs.spigot)
    compileOnly(libs.papi)
    compileOnly(libs.jetbrains.annotations)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
