package com.example.data.remote

import com.example.data.mapper.toEntity
import com.example.domain.entity.Category
import com.example.domain.entity.Popular
import com.example.domain.entity.Trending
import com.example.domain.entity.User
import com.example.domain.exception.EmptyDataException
import com.example.domain.exception.NoInternetException
import com.example.domain.exception.ServerException
import com.example.domain.exception.WrongDataException
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: APIService) : IRemoteDataSource {


    override suspend fun signIn(email: String, password: String, deviceToken: String): User {
        return wrapApiResponse {
            apiService.signIn(email, password, deviceToken)
        }.toEntity()
    }

    override suspend fun signUp(
        email: String,
        name: String,
        password: String,
        phone: String
    ): User {
        return wrapApiResponse {
            apiService.signUp(email, name, password, phone)
        }.toEntity()
    }

    override suspend fun getHomeCategory(): List<Category> {
        return wrapApiResponse {
            apiService.getHomeCategory()
        }.toEntity()
    }

    override suspend fun getHomePopular(lat: Double, lng: Double, filter: Int): List<Popular> {
        return wrapApiResponse {
            apiService.getHomePopular(lat, lng, filter)
        }.toEntity()
    }

    override suspend fun getHomeTrending(lat: Double, lng: Double, filter: Int): List<Trending> {
        return wrapApiResponse {
            apiService.getHomeTrending(lat, lng, filter)
        }.toEntity()
    }

    private suspend fun <T> wrapApiResponse(
        request: suspend () -> Response<T>
    ): T {
        try {
            val response = request()
            if (response.isSuccessful) {
                return response.body() ?: throw EmptyDataException("No data")
            } else {
                throw when (response.code()) {
                    400 ->{
                        val jObjError = JSONObject(
                            response.errorBody()!!.string()
                        )
                        WrongDataException(jObjError.getString("message"))
                    }

                    else -> ServerException("Server error")
                }
            }
        } catch (e: UnknownHostException) {
            throw NoInternetException("No Internet")
        } catch (io: IOException) {
            throw NoInternetException(io.message)
        } catch (e: SocketTimeoutException) {
            throw NoInternetException("Time out,No internet connection")
        }
    }
}