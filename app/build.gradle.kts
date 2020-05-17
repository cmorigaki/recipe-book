plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    id("api-properties")
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
//
//        ApiProperties.load("${rootDir.absolutePath}/buildSrc/api.properties").forEach {
//            manifestPlaceholders[it.key] = it.value
//        }
    }

    buildTypes {
        getByName("release") {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type.
            isMinifyEnabled = true

            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true

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

    implementation(AndroidLibConfig.Dependencies.koinAndroid)

    implementation(project(Project.coreAndroid))
    implementation(project(Project.utilityAndroid))
    implementation(project(Project.utilityKotlin))
    implementation(project(Project.navigation))

    implementation(AndroidLibConfig.Dependencies.fresco)
    implementation(AndroidLibConfig.Dependencies.sentry)

    implementation(project(Features.recipeCollection))
    implementation(project(Features.recipeDetail))
}