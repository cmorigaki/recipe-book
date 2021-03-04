package br.com.recipebook.monitoring.di

import br.com.recipebook.monitoring.ActivityMonitoringWatcher
import br.com.recipebook.monitoring.network.NetworkCallInterceptor
import okhttp3.Interceptor
import org.koin.dsl.bind
import org.koin.dsl.module

val monitoringModule = module {
    single {
        ActivityMonitoringWatcher(application = get())
    }
    factory {
        NetworkCallInterceptor(analytics = get())
    } bind Interceptor::class
}
