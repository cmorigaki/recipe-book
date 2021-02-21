package br.com.recipebook.di

import br.com.recipebook.data.ConfigurationRepositoryImpl
import br.com.recipebook.data.local.ConfigurationDataSourceLocal
import br.com.recipebook.data.local.ConfigurationDataSourceLocalImpl
import br.com.recipebook.data.remote.ConfigurationApi
import br.com.recipebook.data.remote.ConfigurationDataSourceRemote
import br.com.recipebook.data.remote.ConfigurationDataSourceRemoteImpl
import br.com.recipebook.domain.ConfigurationRepository
import org.koin.dsl.module
import retrofit2.Retrofit

val configurationDataModule = module {
    factory<ConfigurationRepository> {
        ConfigurationRepositoryImpl(
            dataSourceLocal = get(),
            dataSourceRemote = get()
        )
    }

    single<ConfigurationDataSourceLocal> {
        ConfigurationDataSourceLocalImpl()
    }

    factory<ConfigurationDataSourceRemote> {
        ConfigurationDataSourceRemoteImpl(
            api = (getKoin().get() as Retrofit).create(ConfigurationApi::class.java)
        )
    }
}

val configurationModules = listOf(
    configurationDataModule
)
