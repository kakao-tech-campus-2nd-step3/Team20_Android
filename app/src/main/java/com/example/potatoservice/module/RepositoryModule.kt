package com.example.potatoservice.module

import com.example.potatoservice.model.APIService
import com.example.potatoservice.ui.home.HomeRepository
import com.example.potatoservice.ui.home.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
	@Provides
	@Singleton
	fun provideRemoteDataSource(apiService: APIService): RemoteDataSource {
		return RemoteDataSource(apiService)
	}
	@Provides
	@Singleton
	fun provideRepository(remoteDataSource: RemoteDataSource): HomeRepository {
		return HomeRepository(remoteDataSource)
	}

}