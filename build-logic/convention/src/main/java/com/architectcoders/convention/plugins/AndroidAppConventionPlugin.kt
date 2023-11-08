package com.architectcoders.convention.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.architectcoders.convention.Config
import com.architectcoders.convention.extensions.configureBuildTypes
import com.architectcoders.convention.extensions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidAppConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }
            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    targetSdk = Config.android.targetSdk
                    applicationId = Config.android.applicationId
                    versionCode = Config.android.versionCode
                    versionName = Config.android.versionName
                }
                configureKotlinAndroid(this)
                configureBuildTypes(this)
            }
        }
    }
}