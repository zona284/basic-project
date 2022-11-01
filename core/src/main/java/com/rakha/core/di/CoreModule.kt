package com.rakha.core.di

import androidx.room.Room
import com.rakha.core.BuildConfig
import com.rakha.core.data.source.local.LocalDataSource
import com.rakha.core.data.source.local.room.MainDatabase
import com.rakha.core.data.source.remote.RemoteDataSource
import com.rakha.core.data.source.remote.service.ApiService
import com.rakha.core.domain.repository.MoviesRepository
import com.rakha.core.domain.repository.MoviesRepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 *   Created By rakha
 *   25/10/22
 */

val databaseModule = module {
    factory { get<MainDatabase>().favoriteMoviesDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MainDatabase::class.java, "main.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(240, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }

    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get()) }
}