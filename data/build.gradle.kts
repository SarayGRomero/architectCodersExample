plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("rickandmortyapp.kotlin.test")
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.javax.inject)
    implementation(libs.arrow.core)
}