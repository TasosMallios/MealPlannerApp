package com.example.mealplannerfinalproject.di

import com.example.mealplannerfinalproject.data.service.AuthServiceAPI
import com.example.mealplannerfinalproject.data.repository.RegisterRepositoryImpl
import com.example.mealplannerfinalproject.domain.repository.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RegisterModule {
    @Provides
    fun provideRegisterRepository(authServiceAPI: AuthServiceAPI): RegisterRepository {
        return RegisterRepositoryImpl(authServiceAPI)
    }
}