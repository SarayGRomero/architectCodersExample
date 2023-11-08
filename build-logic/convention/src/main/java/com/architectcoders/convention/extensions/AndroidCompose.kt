package com.architectcoders.convention.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureAndroidCompose(
    commonExtension: CommonExtension<*, *, *, *, *>
) {
    with(commonExtension) {
        buildFeatures.compose = true
        composeOptions.kotlinCompilerExtensionVersion =
            versionCatalog().findVersion("androidx-compose-compiler").get().toString()

        dependencies {
            dependencies {
                implementationPlatform(lib("androidx-compose-bom"))
                implementationBundle(bundle("compose"))
                androidTestImplementationPlatform(lib("androidx-compose-bom"))
                androidTestImplementation(lib("androidx-ui-test-junit4"))
                debugImplementation(lib("androidx-ui-tooling"))
                debugImplementation(lib("androidx-ui-test-manifest"))
            }
        }
    }
}