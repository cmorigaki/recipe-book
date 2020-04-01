object EnvironmentConfig {
    const val kotlin = "1.3.71"
    const val gradle = "4.0.0-beta03"
    const val jvmTarget = "1.8"

    object Dependencies {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${EnvironmentConfig.kotlin}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${EnvironmentConfig.kotlin}"
        const val gradle = "com.android.tools.build:gradle:${EnvironmentConfig.gradle}"
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
    const val coreKtx = "1.2.0"
    const val appCompat = "1.1.0"
    const val constraintLayout = "1.1.3"

    object Dependencies {
        const val coreKtx = "androidx.core:core-ktx:${AndroidLibConfig.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${AndroidLibConfig.appCompat}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${AndroidLibConfig.constraintLayout}"
    }
}

object TestConfig {
    const val jUnit = "4.12"

    object Dependencies {
        const val jUnit = "junit:junit:${TestConfig.jUnit}"
    }
}
