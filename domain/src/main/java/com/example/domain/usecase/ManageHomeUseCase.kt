package com.example.domain.usecase

import com.example.domain.repo.IRepo
import javax.inject.Inject

class ManageHomeUseCase @Inject constructor(private val iRepo: IRepo) {
    suspend fun getHomeCategory() = iRepo.getHomeCategory()
    suspend fun getHomePopular(lat: Double, lng: Double, filter: Int) =
        iRepo.getHomePopular(lat, lng, filter)
    suspend fun getHomeTrending(lat: Double, lng: Double, filter: Int) =
        iRepo.getHomeTrending(lat, lng, filter)
}