package br.com.recipebook.monitoring.di

import br.com.recipebook.monitoring.ActivityMonitoringWatcher
import org.koin.dsl.module

val monitoringModule = module {
    single {
        ActivityMonitoringWatcher(application = get())
    }
}
