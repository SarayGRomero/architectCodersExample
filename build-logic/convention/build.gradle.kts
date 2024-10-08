plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibCompose") {
            id = "rickandmortyapp.android.lib.compose"
            implementationClass = "com.architectcoders.convention.plugins.AndroidLibComposeConventionPlugin"
        }
        register("androidLib") {
            id = "rickandmortyapp.android.lib"
            implementationClass = "com.architectcoders.convention.plugins.AndroidLibConventionPlugin"
        }
        register("androidApp") {
            id = "rickandmortyapp.android.application"
            implementationClass = "com.architectcoders.convention.plugins.AndroidAppConventionPlugin"
        }
        register("androidAppCompose") {
            id = "rickandmortyapp.android.application.compose"
            implementationClass = "com.architectcoders.convention.plugins.AndroidAppComposeConventionPlugin"
        }
        register("retrofitConvention") {
            id = "rickandmortyapp.retrofit-convention"
            implementationClass = "com.architectcoders.convention.plugins.RetrofitConventionPlugin"
        }
        register("hiltConvention") {
            id = "rickandmortyapp.hilt-convention"
            implementationClass = "com.architectcoders.convention.plugins.HiltConventionPlugin"
        }
        register("roomConvention") {
            id = "rickandmortyapp.room-convention"
            implementationClass = "com.architectcoders.convention.plugins.RoomConventionPlugin"
        }
        register("ksp") {
            id = "rickandmortyapp.ksp"
            implementationClass = "com.architectcoders.convention.plugins.KspConventionPlugin"
        }
    }
}