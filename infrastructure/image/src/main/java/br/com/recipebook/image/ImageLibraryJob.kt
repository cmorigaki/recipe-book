package br.com.recipebook.image

import android.app.Application
import br.com.recipebook.startup.StartupJob

class ImageLibraryJob(private val application: Application) : StartupJob {
    override suspend fun invoke() {
        // FIXME Unused after compose migration
    }
}
