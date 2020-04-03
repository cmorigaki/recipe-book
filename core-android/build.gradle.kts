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

        consumerProguardFile("consumer-rules.pro")
    }

    buildFeatures {
        viewBinding = true
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
    implementation(EnvironmentConfig.Dependencies.kotlinStdLib)
    implementation(AndroidLibConfig.Dependencies.coreKtx)

    testImplementation(TestConfig.Dependencies.jUnit)
}