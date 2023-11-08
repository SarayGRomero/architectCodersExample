package com.architectcoders.convention.extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Retrieves the Version Catalog associated with this project.
 *
 * @return The VersionCatalogsExtension named "libs" if found, otherwise null.
 */
internal fun Project.versionCatalog() = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun Project.lib(name: String) = versionCatalog().findLibrary(name).get()

internal fun Project.bundle(name: String) = versionCatalog().findBundle(name).get()