plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "at.helpch"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly(libs.spigot)
    implementation("org.jetbrains:annotations:24.0.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks {
    shadowJar {
        relocate("org.jetbrains", "at.helpch.ea.libs.jetbrains")
        relocate("org.intellij", "at.helpch.ea.libs.intellij")
        archiveFileName.set("EnhancedActions-${rootProject.version}.jar")
    }
}
