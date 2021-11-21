import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
    application
}

group = "com.lkorasik"
version = "0.1"

repositories {
    jcenter()
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    // Auth
    implementation("io.ktor:ktor-auth:1.6.4")
    implementation("io.ktor:ktor-auth-jwt:1.6.5")

    // Serialization
    implementation("io.ktor:ktor-jackson:1.6.5")

    // Server
    val ktor_version = "1.6.4"
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-html-builder:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:0.7.3")
}

tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("ServerKt")
}