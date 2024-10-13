plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
    id("rickandmortyapp.kotlin.test")
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(libs.javax.inject)
    implementation(libs.kotlinx.coroutines.core)
}