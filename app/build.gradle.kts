plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(apiLevel = AndroidConfig.compileSdk)
    buildToolsVersion(AndroidConfig.buildTools)

    defaultConfig {
        applicationId = AndroidConfig.applicationId

        minSdkVersion(AndroidConfig.minSdk)
        targetSdkVersion(AndroidConfig.targetSdk)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
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
    implementation(EnvironmentConfig.Dependencies.kotlin)
    implementation(AndroidLibConfig.Dependencies.coreKtx)
    implementation(AndroidLibConfig.Dependencies.appCompat)
    implementation(AndroidLibConfig.Dependencies.constraintLayout)

    testImplementation(TestConfig.Dependencies.jUnit)

    implementation(project(":features:recipe-collection:impl"))
}