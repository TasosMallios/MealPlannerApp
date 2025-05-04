package com.example.mealplannerfinalproject.di

import com.example.mealplannerfinalproject.data.service.AuthServiceAPI
import com.example.mealplannerfinalproject.data.repository.LoginRepositoryImpl
import com.example.mealplannerfinalproject.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoginModule {
    @Provides
    fun provideLoginRepository(authServiceAPI: AuthServiceAPI): LoginRepository {
        return LoginRepositoryImpl(authServiceAPI)
    }
}