plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(apiLevel = AndroidConfig.compileSdk)
    buildToolsVersion (AndroidConfig.buildTools)

    defaultConfig {
        minSdkVersion(AndroidConfig.minSdk)
        targetSdkVersion(AndroidConfig.targetSdk)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = EnvironmentConfig.jvmTarget
    }
}

dependencies {
    implementation(EnvironmentConfig.Dependencies.kotlinStdLib)
    implementation(AndroidLibConfig.Dependencies.coreKtx)

    // For theme
    implementation(AndroidLibConfig.Dependencies.appCompat)
    implementation(AndroidLibConfig.Dependencies.recyclerView)
    implementation(AndroidLibConfig.Dependencies.constraintLayout)
    implementation(AndroidLibConfig.Dependencies.material)

    implementation(AndroidLibConfig.Dependencies.koinAndroid)

    implementation(AndroidLibConfig.Dependencies.retrofit)
    implementation(AndroidLibConfig.Dependencies.moshiConverter)
    implementation(AndroidLibConfig.Dependencies.moshi)

    implementation(project(Project.utilityAndroid))
    implementation(project(Project.utilityKotlin))

    testImplementation(TestConfig.Dependencies.jUnit)
    testImplementation(TestConfig.Dependencies.mockk)
}