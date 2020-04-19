package br.com.recipebook.coreandroid.image

import br.com.recipebook.coreandroid.configuration.Configuration
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ImageResolverTest {

    private val imageResolver = ImageResolver()

    @Test
    fun `mountUrl empty url`() {
        val url = imageResolver.mountUrl("", ImageSize.SMALL)

        assertNull(url)
    }

    @Test
    fun `mountUrl null url`() {
        val url = imageResolver.mountUrl(null, ImageSize.SMALL)

        assertNull(url)
    }

    @Test
    fun `mountUrl without placeholder`() {
        val url = imageResolver.mountUrl("macarronada.png", ImageSize.SMALL)

        val expected = Configuration.IMG_URL + "macarronada.png"
        assertEquals(expected, url)
    }

    @Test
    fun `mountUrl with SMALL placeholder`() {
        val url = imageResolver.mountUrl("{size}/macarronada.png", ImageSize.SMALL)

        val expected = Configuration.IMG_URL + "400/macarronada.png"
        assertEquals(expected, url)
    }

    @Test
    fun `mountUrl with MEDIUM placeholder`() {
        val url = imageResolver.mountUrl("{size}/macarronada.png", ImageSize.MEDIUM)

        val expected = Configuration.IMG_URL + "800/macarronada.png"
        assertEquals(expected, url)
    }

    @Test
    fun `mountUrl with LARGE placeholder`() {
        val url = imageResolver.mountUrl("{size}/macarronada.png", ImageSize.LARGE)

        val expected = Configuration.IMG_URL + "/macarronada.png"
        assertEquals(expected, url)
    }
}