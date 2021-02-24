package br.com.recipebook.inappupdate.di

import br.com.recipebook.inappupdate.domain.CheckInAppUpdate
import br.com.recipebook.inappupdate.domain.CheckInAppUpdateUseCase
import br.com.recipebook.inappupdate.domain.InAppUpdater
import br.com.recipebook.inappupdate.view.InAppUpdaterImpl
import org.koin.dsl.module

val inAppUpdateDomainModule = module {
    factory<CheckInAppUpdateUseCase> {
        CheckInAppUpdate(
            buildConfiguration = get(),
            configurationRepository = get(),
            inAppUpdater = get(),
        )
    }
}

val inAppUpdateViewModule = module {
    factory<InAppUpdater> {
        InAppUpdaterImpl(
            activityProvider = get(),
        )
    }
}

val inAppUpdateModules = listOf(
    inAppUpdateDomainModule,
    inAppUpdateViewModule
)
