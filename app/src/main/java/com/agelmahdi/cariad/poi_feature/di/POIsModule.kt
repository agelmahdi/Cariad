package com.agelmahdi.cariad.poi_feature.di

import com.agelmahdi.cariad.core.util.Constance
import com.agelmahdi.cariad.poi_feature.data.remote.POIsAPI
import com.agelmahdi.cariad.poi_feature.data.repository_impl.POIRepositoryImpl
import com.agelmahdi.cariad.poi_feature.domain.repository.POIRepository
import com.agelmahdi.cariad.poi_feature.domain.use_case.GetPoiUseCase
import com.agelmahdi.cariad.poi_feature.domain.use_case.PoiUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    ): POIRepository {
        return POIRepositoryImpl(api)
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

}