package com.example.protolink.di

import com.example.protolink.data.TemplateRepository
import com.example.protolink.data.TemplateRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindTemplateRepository(repository: TemplateRepositoryImpl): TemplateRepository
}
