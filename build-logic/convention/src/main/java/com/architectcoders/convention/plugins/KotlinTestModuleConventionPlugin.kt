package com.architectcoders.convention.plugins

import com.architectcoders.convention.Config
import com.architectcoders.convention.extensions.configureKotlinTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure

class KotlinTestModuleConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            target.plugins.apply("java-library")
            target.plugins.apply("org.jetbrains.kotlin.jvm")
            target.extensions.configure<JavaPluginExtension> {
                configureKotlinTest(this)
                sourceCompatibility = Config.jvm.javaVersion
                targetCompatibility = Config.jvm.javaVersion
            }
        }
    }
}