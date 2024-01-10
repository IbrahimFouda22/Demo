package com.example.data.repo

import com.example.data.remote.IRemoteDataSource
import com.example.domain.entity.Trending
import com.example.domain.repo.IRepo
import javax.inject.Inject

class RepoImpl @Inject constructor(private val remoteDataSource: IRemoteDataSource) : IRepo {
    override suspend fun signIn(email: String, password: String, deviceToken: String) =
        remoteDataSource.signIn(email, password, deviceToken)

    override suspend fun signUp(
        email: String,
        name: String,
        password: String,
        phone: String
    ) = remoteDataSource.signUp(email, name, password, phone)

    override suspend fun getHomeCategory() = remoteDataSource.getHomeCategory()
    override suspend fun getHomePopular(lat: Double, lng: Double, filter: Int) =
        remoteDataSource.getHomePopular(lat, lng, filter)

    override suspend fun getHomeTrending(lat: Double, lng: Double, filter: Int)=
        remoteDataSource.getHomeTrending(lat, lng, filter)
}