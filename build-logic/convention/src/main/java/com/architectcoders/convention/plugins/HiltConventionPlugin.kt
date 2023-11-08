package com.architectcoders.convention.plugins

import com.architectcoders.convention.extensions.implementation
import com.architectcoders.convention.extensions.ksp
import com.architectcoders.convention.extensions.lib
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.dagger.hilt.android")
            dependencies {
                implementation(lib("hilt"))
                ksp(lib("hilt-compiler"))
            }
        }
    }
}