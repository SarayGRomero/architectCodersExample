package com.architectcoders.convention.extensions

import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType

internal fun Project.configureAppTest(
    commonExtension: Any,
) {
    commonExtension.apply {
        dependencies {
            add("testImplementation", project(":appTestShared"))
            add("kspAndroidTest", lib("hilt-compiler"))
            androidTestImplementation(lib("hilt-test"))
            androidTestImplementation(lib("okhttp-mockwebserver"))
        }
    }
    tasks.withType<Test> {
        useJUnitPlatform()
    }
}