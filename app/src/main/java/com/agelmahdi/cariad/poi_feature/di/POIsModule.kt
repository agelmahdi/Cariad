package com.agelmahdi.cariad.poi_feature.di

import android.content.Context
import androidx.room.Room
import com.agelmahdi.cariad.core.util.Constance
import com.agelmahdi.cariad.core.util.GsonParser
import com.agelmahdi.cariad.poi_feature.data.local.Converters
import com.agelmahdi.cariad.poi_feature.data.local.POIDatabase
import com.agelmahdi.cariad.poi_feature.data.remote.POIsAPI
import com.agelmahdi.cariad.poi_feature.data.repository_impl.POIRepositoryImpl
import com.agelmahdi.cariad.poi_feature.domain.repository.POIRepository
import com.agelmahdi.cariad.poi_feature.domain.use_case.GetPoiUseCase
import com.agelmahdi.cariad.poi_feature.domain.use_case.PoiUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object POIsModule {

    @Provides
    @Singleton
    fun provideGetPOIsInfoUseCase(repository: POIRepository): PoiUseCase {
        return GetPoiUseCase(repository)
    }

    @Provides
    @Singleton
    fun providePOIsRepository(
        api: POIsAPI,
        poiDatabase: POIDatabase
    ): POIRepository {
        return POIRepositoryImpl(api, poiDatabase)
    }

    @Provides
    @Singleton
    fun providePOIsAPI(): POIsAPI {
        return Retrofit.Builder()
            .baseUrl(Constance.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(POIsAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext appContext: Context): POIDatabase {
        return Room.databaseBuilder(
            appContext,
            POIDatabase::class.java,
            "poi_db"
        ).addTypeConverter(Converters(GsonParser(Gson())))
            .build()
    }
}