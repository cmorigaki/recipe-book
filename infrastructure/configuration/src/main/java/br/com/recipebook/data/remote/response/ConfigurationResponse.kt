package br.com.recipebook.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationResponse(
    val minimumVersionCode: Int?,
    val excludedVersionCodes: List<Int>?,
)
