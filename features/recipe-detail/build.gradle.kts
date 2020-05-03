plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
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

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "${project.projectDir}/buildSrc/proguard-rules-network.pro"
            )
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
    implementation(AndroidLibConfig.Dependencies.coroutinesCore)

    implementation(AndroidLibConfig.Dependencies.koinLifecycle)
    implementation(AndroidLibConfig.Dependencies.koinViewModel)
    implementation(AndroidLibConfig.Dependencies.koinFragment)

    implementation(AndroidLibConfig.Dependencies.retrofit)
    implementation(AndroidLibConfig.Dependencies.moshiConverter)
    implementation(AndroidLibConfig.Dependencies.moshi)
    kapt(AndroidLibConfig.Dependencies.moshiCodeGen)

    implementation(AndroidLibConfig.Dependencies.appCompat)
    implementation(AndroidLibConfig.Dependencies.constraintLayout)
    implementation(AndroidLibConfig.Dependencies.recyclerView)
    implementation(AndroidLibConfig.Dependencies.cardView)
    implementation(AndroidLibConfig.Dependencies.viewmodel)
    implementation(AndroidLibConfig.Dependencies.livedata)
    implementation(AndroidLibConfig.Dependencies.fragment)
    implementation(AndroidLibConfig.Dependencies.swipeRefresh)
    implementation(AndroidLibConfig.Dependencies.coordinatorLayout)
    implementation(AndroidLibConfig.Dependencies.material)

    implementation(AndroidLibConfig.Dependencies.fresco)

    testImplementation(TestConfig.Dependencies.jUnit)

    implementation(project(Project.utilityAndroid))
    implementation(project(Project.utilityKotlin))
    implementation(project(Project.coreAndroid))
    implementation(project(Project.navigation))
}