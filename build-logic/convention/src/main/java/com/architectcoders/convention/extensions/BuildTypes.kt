package com.architectcoders.convention.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project

fun Project.configureBuildTypes(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    with(commonExtension) {
        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
    }
}