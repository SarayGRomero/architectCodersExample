plugins {
    id("rickandmortyapp.android.application")
    id("rickandmortyapp.android.application.compose")
    id("rickandmortyapp.ksp")
    id("rickandmortyapp.hilt-convention")
    id("rickandmortyapp.retrofit-convention")
    id("rickandmortyapp.room-convention")
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