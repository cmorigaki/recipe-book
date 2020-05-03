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

    implementation(AndroidLibConfig.Dependencies.koinAndroid)

    implementation(project(Project.coreAndroid))
    implementation(project(Project.utilityAndroid))
    implementation(project(Project.utilityKotlin))
}