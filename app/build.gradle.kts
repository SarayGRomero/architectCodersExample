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

    testImplementation(project(":testShared"))
    testImplementation(project(":appTestShared"))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito.jupiter)
}

tasks.withType<Test> {
    useJUnitPlatform()
}