plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlinSerialization)
}

group = "com.zeus"
version = "0.0.1"

application {
    mainClass.set("com.zeus.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.serialization)
    implementation(libs.ktor.server.contentNegotiation)
    implementation(libs.ktor.server.cors)

    implementation(libs.postgress)

    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)

    implementation(libs.ktor.server.partialContent)
    implementation(libs.ktor.server.autoHeadResponse)

    implementation(libs.ktor.server.serializationGson)
    implementation(libs.ktor.server.sessions)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.jwt)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.tests)
}
