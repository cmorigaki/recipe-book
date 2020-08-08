package br.com.recipebook.image.di

import br.com.recipebook.image.ImageLibraryJob
import br.com.recipebook.startup.StartupJob
import org.koin.dsl.bind
import org.koin.dsl.module

val imageModule = module {
    factory {
        ImageLibraryJob(application = get())
    } bind StartupJob::class
}
