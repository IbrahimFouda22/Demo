package com.example.demo.di

import com.example.data.remote.RemoteDataSource
import com.example.data.repo.RepoImpl
import com.example.domain.repo.IRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(remoteDataSource: RemoteDataSource): IRepo = RepoImpl(remoteDataSource)
}