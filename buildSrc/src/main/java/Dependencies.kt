object EnvironmentConfig {
    private const val kotlin = "1.3.71"
    private const val gradle = "4.0.0-beta04"
    const val jvmTarget = "1.8"

    object Dependencies {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin"
        const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin"
        const val kotlinJdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin"
        const val gradleTools = "com.android.tools.build:gradle:$gradle"
    }
}

object AndroidConfig {
    const val applicationId = "br.com.recipebook"
    const val versionCode = 1
    const val versionName = "1.0"

    const val compileSdk = 29
    const val buildTools = "29.0.3"
    const val minSdk = 23
    const val targetSdk = 29
}

object AndroidLibConfig {
    object Dependencies {
        const val coreKtx = "androidx.core:core-ktx:1.2.0"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.1.1"

        const val retrofit = "com.squareup.retrofit2:retrofit:2.8.1"
        const val moshiConverter = "com.squareup.retrofit2:converter-moshi:2.8.1"
        const val moshiCodeGen = "com.squareup.moshi:moshi-kotlin-codegen:1.9.2"
        const val moshi = "com.squareup.moshi:moshi:1.9.2"

        const val appCompat = "androidx.appcompat:appcompat:1.1.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.1.0"
        const val cardView = "androidx.cardview:cardview:1.0.0"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.2.2"

        private const val koinVersion = "2.1.5"
        const val koinAndroid = "org.koin:koin-android:$koinVersion" // Koin for Android
        const val koinLifecycle =
            "org.koin:koin-androidx-scope:$koinVersion" // or Koin for Lifecycle scoping
        const val koinViewModel =
            "org.koin:koin-androidx-viewmodel:$koinVersion" // or Koin for Android Architecture ViewModel
        const val koinFragment =
            "org.koin:koin-androidx-fragment:$koinVersion" // or Koin for Android Fragment Factory (unstable version)

        const val fresco = "com.facebook.fresco:fresco:2.2.0"
    }
}

object Project {
    const val utilityKotlin = ":utility-kotlin"
    const val utilityAndroid = ":utility-android"
    const val coreAndroid = ":core-android"
}

object Features {
    const val recipeCollectionImpl = ":features:recipe-collection:impl"
}

object TestConfig {
    private const val jUnit = "4.12"

    object Dependencies {
        const val jUnit = "junit:junit:${TestConfig.jUnit}"
    }
}
