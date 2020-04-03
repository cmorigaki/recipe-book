object EnvironmentConfig {
    private const val kotlin = "1.3.71"
    private const val gradle = "4.0.0-beta03"
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
    private const val coreKtx = "1.2.0"
    private const val appCompat = "1.1.0"
    private const val constraintLayout = "1.1.3"
    private const val recyclerView = "1.1.0"
    private const val cardView = "1.0.0"

    object Dependencies {
        const val coreKtx = "androidx.core:core-ktx:${AndroidLibConfig.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${AndroidLibConfig.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${AndroidLibConfig.constraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${AndroidLibConfig.recyclerView}"
        const val cardView = "androidx.cardview:cardview:${AndroidLibConfig.cardView}"
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
