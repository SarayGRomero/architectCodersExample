package com.architectcoders.convention

import org.gradle.api.JavaVersion

object Config {
    val android = AndroidConfig(
        minSdk = 26,
        compileSdk = 34,
        versionCode = 1,
        versionName = "1.0",
        targetSdk = 34,
        applicationId = "com.architectcoders.rickandmortyapp"
    )
    val jvm = JvmConfig(
        javaVersion = JavaVersion.VERSION_1_8,
        kotlinJvm = "1.8"
    )
}

data class AndroidConfig(
    val minSdk: Int,
    val compileSdk: Int,
    val versionCode: Int,
    val versionName: String,
    val targetSdk: Int,
    val applicationId: String
)

data class JvmConfig(
    val javaVersion: JavaVersion,
    val kotlinJvm: String
)