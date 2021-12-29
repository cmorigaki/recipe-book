package br.com.recipebook.coreandroid.image

import br.com.recipebook.coreandroid.configuration.Configuration

// FIXME improve this image resolution resolver
enum class ImageSize(val folder: String) {
    SMALL("400"),
    MEDIUM("800"),
    LARGE("original")
}

private const val SizePlaceholder = "{size}"

class ImageResolver {
    fun mountUrl(relativePath: String?, size: ImageSize): String? {
        return if (!relativePath.isNullOrBlank()) {
            Configuration.IMG_URL + relativePath.replace(SizePlaceholder, size.folder)
        } else {
            null
        }
    }
}
