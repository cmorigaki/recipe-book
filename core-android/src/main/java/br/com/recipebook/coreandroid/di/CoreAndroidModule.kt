package br.com.recipebook.coreandroid.di

import br.com.recipebook.utilityandroid.network.BigDecimalAdapter
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val coreAndroidModule = module {
    single {
        val interceptors: List<Interceptor> = getAll()
        OkHttpClient.Builder().apply {
            interceptors.forEach {
                addInterceptor(it)
            }
        }.build()
    }
    single<Converter.Factory> {
        MoshiConverterFactory.create(
            Moshi.Builder()
                .add(BigDecimalAdapter)
                .build()
        )
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://d20y9ud0nwoqf1.cloudfront.net/")
            .client(get())
            .addConverterFactory(get())
            .build()
    }
}
