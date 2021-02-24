package br.com.recipebook.domain.model

data class AppUpdateInfoModel(
    val minimumVersionCode: Int?,
    val excludedVersionCodes: List<Int>,
)
