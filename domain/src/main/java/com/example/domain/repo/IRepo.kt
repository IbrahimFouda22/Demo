package com.example.domain.repo

import com.example.domain.entity.Category
import com.example.domain.entity.Popular
import com.example.domain.entity.Trending
import com.example.domain.entity.User

interface IRepo {
    suspend fun signIn(email: String, password: String, deviceToken: String): User
    suspend fun signUp(email: String, name: String, password: String, phone: String): User
    suspend fun getHomeCategory(): List<Category>
    suspend fun getHomePopular(lat:Double,lng:Double,filter:Int): List<Popular>
    suspend fun getHomeTrending(lat:Double,lng:Double,filter:Int): List<Trending>


}