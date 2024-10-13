package com.architectcoders.convention.extensions

import com.android.build.api.dsl.CommonExtension
import com.architectcoders.convention.Config
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    with(commonExtension) {
        compileSdk = Config.android.compileSdk
        defaultConfig {
            minSdk = Config.android.minSdk
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
        compileOptions {
            sourceCompatibility = Config.jvm.javaVersion
            targetCompatibility = Config.jvm.javaVersion
        }
        dependencies {
            implementation(lib("androidx-core-ktx"))
            implementation(lib("androidx-lifecycle-runtime-ktx"))
            testImplementation(lib("coroutines-test"))
            testImplementation(lib("turbine"))
            androidTestImplementation(lib("androidx-junit"))
            androidTestImplementation(lib("androidx-espresso-core"))
            androidTestImplementation(lib("androidx-espresso-contrib"))
            androidTestImplementation(lib("coroutines-test"))
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = Config.jvm.kotlinJvm
        }
    }
}