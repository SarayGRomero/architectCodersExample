plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}