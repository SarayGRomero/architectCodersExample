package com.architectcoders.convention.extensions

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal fun Project.configureKotlinTest(
    commonExtension: Any,
) {
    commonExtension.apply {
        dependencies {
            add("testImplementation", project(":testShared"))
            testImplementation(lib("junit"))
            testImplementation(lib("mockito-kotlin"))
            testImplementation(lib("mockito-inline"))
            testImplementation(lib("junit-jupiter"))
            testImplementation(lib("mockito-jupiter"))
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}