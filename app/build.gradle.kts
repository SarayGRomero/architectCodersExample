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

    defaultConfig {
        testInstrumentationRunner = "com.architectcoders.rickandmortyapp.di.HiltTestRunner"
    }
}

dependencies {
    implementation(libs.coil)
    implementation(libs.arrow.core)
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    testImplementation(project(":testShared"))
    testImplementation(project(":appTestShared"))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.jupiter)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.hilt.test)
    androidTestImplementation(libs.okhttp.mockwebserver)
}

tasks.withType<Test> {
    useJUnitPlatform()
}