plugins {
    id("rickandmortyapp.android.application")
    id("rickandmortyapp.android.application.compose")
    id("rickandmortyapp.ksp")
    id("rickandmortyapp.hilt")
    id("rickandmortyapp.retrofit")
    id("rickandmortyapp.room")
    id("rickandmortyapp.app.test")
}

android {
    namespace = "com.architectcoders.rickandmortyapp"
}

dependencies {
    implementation(libs.coil)
    implementation(libs.arrow.core)
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))
}