package com.architectcoders.convention.extensions

import org.gradle.api.artifacts.ExternalModuleDependencyBundle
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.provider.Provider

internal fun DependencyHandler.implementation(libraryProvider: Provider<MinimalExternalModuleDependency>) {
    add("implementation", libraryProvider.get())
}

internal fun DependencyHandler.testImplementation(libraryProvider: Provider<MinimalExternalModuleDependency>) {
    add("testImplementation", libraryProvider.get())
}

internal fun DependencyHandler.androidTestImplementation(libraryProvider: Provider<MinimalExternalModuleDependency>) {
    add("androidTestImplementation", libraryProvider.get())
}

internal fun DependencyHandler.implementationPlatform(libraryProvider: Provider<MinimalExternalModuleDependency>) {
    add("implementation", platform(libraryProvider.get()))
}

internal fun DependencyHandler.androidTestImplementationPlatform(
    libraryProvider: Provider<MinimalExternalModuleDependency>
) {
    add("androidTestImplementation", platform(libraryProvider.get()))
}

fun DependencyHandler.implementationBundle(bundleProvider: Provider<ExternalModuleDependencyBundle>) {
    bundleProvider.get().forEach { dependencyNotation ->
        add("implementation", dependencyNotation)
    }
}

internal fun DependencyHandler.debugImplementation(libraryProvider: Provider<MinimalExternalModuleDependency>) {
    add("debugImplementation", libraryProvider.get())
}

internal fun DependencyHandler.ksp(libraryProvider: Provider<MinimalExternalModuleDependency>) {
    add("ksp", libraryProvider.get())
}