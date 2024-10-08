package com.architectcoders.convention.plugins

import com.android.build.api.dsl.LibraryExtension
import com.architectcoders.convention.extensions.configureBuildTypes
import com.architectcoders.convention.extensions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("kotlin-android")
            }
            extensions.configure<LibraryExtension> {
                configureKotlinAndroid(this)
                configureBuildTypes(this)
            }
        }
    }
}