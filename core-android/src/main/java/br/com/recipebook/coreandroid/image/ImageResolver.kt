package br.com.recipebook.coreandroid.image

import br.com.recipebook.coreandroid.configuration.Configuration

class ImageResolver {
    fun mountUrl(relativePath: String?): String? {
        return if (!relativePath.isNullOrBlank()) {
            Configuration.IMG_URL + relativePath
        } else {
            null
        }
    }
}