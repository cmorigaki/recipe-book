package br.com.recipebook.image

import android.app.Application
import br.com.recipebook.startup.StartupJob
import com.facebook.drawee.backends.pipeline.Fresco

class ImageLibraryJob(private val application: Application) : StartupJob {
    override suspend fun invoke() {
        Fresco.initialize(application)
    }
}
