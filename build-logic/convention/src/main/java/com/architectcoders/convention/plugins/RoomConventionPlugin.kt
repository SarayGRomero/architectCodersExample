package com.architectcoders.convention.plugins

import com.architectcoders.convention.extensions.bundle
import com.architectcoders.convention.extensions.implementationBundle
import com.architectcoders.convention.extensions.ksp
import com.architectcoders.convention.extensions.lib
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class RoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            dependencies {
                implementationBundle(bundle("room"))
                ksp(lib("room-compiler"))
            }
        }
    }
}