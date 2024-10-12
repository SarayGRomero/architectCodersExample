plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("rickandmortyapp.android.lib")
}

android {
    namespace = "com.architectcoders.apptestshared"
}

dependencies {
    implementation(project(":app"))
    implementation(project(":data"))
}