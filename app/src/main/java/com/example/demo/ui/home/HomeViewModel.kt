package com.example.demo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.entity.Category
import com.example.domain.entity.Popular
import com.example.domain.entity.Trending
import com.example.domain.usecase.ManageHomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val manageHomeUseCase: ManageHomeUseCase) :
    ViewModel() {
    private val _homeCatUiState = MutableStateFlow(HomeUiState())
    val homeCatUiState = _homeCatUiState as StateFlow<HomeUiState>

    private val _homePopularUiState = MutableStateFlow(HomeUiState())
    val homePopularUiState = _homePopularUiState as StateFlow<HomeUiState>

    private val _homeTrendingUiState = MutableStateFlow(HomeUiState())
    val homeTrendingUiState = _homeTrendingUiState as StateFlow<HomeUiState>

    init {
        getHomeCat()
        getHomePopular(29.1931, 30.6421, 1)
        getHomeTrending(29.1931, 30.6421, 1)
    }

    private fun getHomeCat() {
        _homeCatUiState.update {
            HomeUiState(
                isLoading = true,
                error = null,
            )
        }
        viewModelScope.launch {
            try {
                onGetHomeCatSuccess(manageHomeUseCase.getHomeCategory())
            } catch (e: Exception) {
                onGetHomeCatFailure(e.message.toString())
            }
        }
    }

    private fun onGetHomeCatSuccess(list: List<Category>) {
        _homeCatUiState.update {
            HomeUiState(
                isLoading = false,
                error = null,
                categoryUiState = list.map {
                    CategoryUiState(
                        id = it.id,
                        nameEn = it.nameEn,
                        nameAr = it.nameAr,
                        name = it.name,
                        image = it.image
                    )
                }
            )
        }
    }

    private fun onGetHomeCatFailure(error: String?) {
        _homeCatUiState.update {
            HomeUiState(
                isLoading = false,
                error = error,
                categoryUiState = arrayListOf()
            )
        }
    }


    private fun getHomePopular(lat: Double, lng: Double, filter: Int) {
        _homePopularUiState.update {
            HomeUiState(
                isLoading = true,
                error = null,
            )
        }
        viewModelScope.launch {
            try {
                onGetHomePopularSuccess(manageHomeUseCase.getHomePopular(lat, lng, filter))
            } catch (e: Exception) {
                onGetHomePopularFailure(e.message.toString())
            }
        }
    }

    private fun onGetHomePopularSuccess(list: List<Popular>) {
        _homePopularUiState.update {
            HomeUiState(
                isLoading = false,
                error = null,
                popularUiState = list.map {
                    PopularUiState(
                        id = it.id,
                        name = it.name,
                        email = it.email,
                        image = it.image,
                        address = it.address,
                        distance = it.distance,
                        rate = it.rate,
                        isFavorite = it.is_favorite,
                    )
                }
            )
        }
    }

    private fun onGetHomePopularFailure(error: String?) {
        _homePopularUiState.update {
            HomeUiState(
                isLoading = false,
                error = error,
                popularUiState = arrayListOf()
            )
        }
    }

    private fun getHomeTrending(lat: Double, lng: Double, filter: Int) {
        _homeTrendingUiState.update {
            HomeUiState(
                isLoading = true,
                error = null,
            )
        }
        viewModelScope.launch {
            try {
                onGetHomeTrendingSuccess(manageHomeUseCase.getHomeTrending(lat, lng, filter))
            } catch (e: Exception) {
                onGetHomeTrendingFailure(e.message.toString())
            }
        }
    }

    private fun onGetHomeTrendingSuccess(list: List<Trending>) {
        _homeTrendingUiState.update {
            HomeUiState(
                isLoading = false,
                error = null,
                trendingUiState = list.map {
                    TrendingUiState(
                        logo = it.logo,
                    )
                }
            )
        }
    }

    private fun onGetHomeTrendingFailure(error: String?) {
        _homeTrendingUiState.update {
            HomeUiState(
                isLoading = false,
                error = error,
                trendingUiState = arrayListOf()
            )
        }
    }

}