package br.com.recipebook.device

import java.util.Locale

private val SUPPORTED_LANGUAGES = listOf("en", "pt")

interface LocaleProvider {
    fun getLocale(): String
}

internal class LocaleProviderImpl : LocaleProvider {
    override fun getLocale(): String {
        return Locale.getDefault().language.takeIf { SUPPORTED_LANGUAGES.contains(it) } ?: "en"
    }
}
