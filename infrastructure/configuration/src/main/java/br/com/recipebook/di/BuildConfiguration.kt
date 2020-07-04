package br.com.recipebook.di

data class BuildConfiguration(
    val appInfo: AppInfo,
    val apiKeys: ApiKeys
)

data class AppInfo(
    val name: String,
    val version: String,
    val variant: String,
    val buildVariant: BuildVariant
)

data class ApiKeys(
    val sentryKey: String,
    val amplitudeKey: String
)

enum class BuildVariant {
    RELEASE,
    DEBUG
}