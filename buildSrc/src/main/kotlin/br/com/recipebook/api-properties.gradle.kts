package br.com.recipebook

import ApiProperties

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.3.72")
}

//android {
//    defaultConfig {
//        ApiProperties.load("${rootDir.absolutePath}/buildSrc/api.properties").forEach {
//            manifestPlaceholders[it.key] = it.value
//        }
//    }
//}