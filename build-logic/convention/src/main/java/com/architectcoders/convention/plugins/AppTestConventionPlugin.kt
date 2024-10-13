package com.architectcoders.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.architectcoders.convention.Config
import com.architectcoders.convention.extensions.configureAppTest
import com.architectcoders.convention.extensions.configureKotlinTest
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure

class AppTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            target.extensions.configure<ApplicationExtension> {
                defaultConfig {
                    testInstrumentationRunner = "com.architectcoders.rickandmortyapp.di.HiltTestRunner"
                }
                configureKotlinTest(this)
                configureAppTest(this)
            }
        }
    }
}