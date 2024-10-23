package com.example.myproject.presentaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject.data.model.CategoriesRequest
import com.example.myproject.data.model.HomeResponse
import com.example.myproject.data.model.LoginRequest
import com.example.myproject.data.model.LoginResponse
import com.example.myproject.data.model.Product
import com.example.myproject.data.model.RegisterRequest
import com.example.myproject.data.model.RegisterResponse
import com.example.myproject.data.model.cart.AddOrDeleteCartRequest
import com.example.myproject.data.model.cart.CartResponse
import com.example.myproject.data.model.favourite.AddOrDeleteFavouriteRequest
import com.example.myproject.data.model.favourite.FavouritesResponse
import com.example.myproject.data.model.search.SearchRequest
import com.example.myproject.data.model.search.SearchResponse
import com.example.myproject.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> get() = _loginResponse

    //    login
    fun login(loginRequest: LoginRequest, onSuccess: (String) -> Unit,onError: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.login(loginRequest)
                if (response.isSuccessful && response.body()?.status == true) {
                    _loginResponse.value = response.body()
                    onSuccess(response.body()?.data?.token ?: "")
                } else {
                    onError(response.body()?.status == false)
                }
                } catch (Exception: Exception) {
                println(Exception)
            }
        }
    }

    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> get() = _registerResponse
// register

    fun register(registerRequest: RegisterRequest) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.register(registerRequest)
                if (response.isSuccessful) {
                    _registerResponse.value = response.body()
                }
            } catch (Exception: Exception) {
                println(Exception)
            }
        }
    }

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _homeResponse = MutableStateFlow<HomeResponse?>(null)
    val homeResponse: StateFlow<HomeResponse?> get() = _homeResponse
//    getHomeData

    fun getHomeData(
        token: String,
        lang: String,
    ) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response =
                    RetrofitInstance.apiService.getHomeData(token, lang)
                if (response.isSuccessful) {
                    _homeResponse.value = response.body()
                }

            } catch (Exception: Exception) {
                println(Exception)
            } finally {
                _isLoading.value = false

            }
        }
    }

    //    favourites
    private val _favouriteResponse = MutableStateFlow<FavouritesResponse?>(null)
    val favouriteResponse: StateFlow<FavouritesResponse?> get() = _favouriteResponse
    fun getFavourites(token: String, lang: String) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.getFavouritesData(token, lang)
                if (response.isSuccessful) {
                    _favouriteResponse.value = response.body()
                }
            } catch (Exception: Exception) {
                println(Exception)
            }
        }
    }

    fun getAddFvourites(
        token: String,
        addOrDeleteFavouriteRequest: AddOrDeleteFavouriteRequest,
        onSuccess: (String?) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.addOrDeleteFavourites(
                        token = token,
                        addOrDeleteFavouriteRequest = addOrDeleteFavouriteRequest
                    )
                if (response.isSuccessful) {
                    onSuccess(response.body()?.message)
                }
            } catch (Exception: Exception) {
                println(Exception)
            }

        }
    }
//    Cart
    private val _cartResponse = MutableStateFlow<CartResponse?>(null)
    val cartResponse: StateFlow<CartResponse?> get() = _cartResponse
    fun getCartData(token: String, lang: String) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.getCartProducts(token, lang)
                if (response.isSuccessful) {
                    _cartResponse.value = response.body()
                }
            } catch (Exception: Exception) {
                println(Exception)
            }
        }
    }

    fun getAddCart(
        token: String,
        addOrDeleteCartRequest: AddOrDeleteCartRequest,
        onSuccess: (String?) -> Unit,
    ) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.addOrDeleteCart(
                        token = token,
                        addOrDeleteCartRequest = addOrDeleteCartRequest
                    )
                if (response.isSuccessful) {
                    onSuccess(response.body()?.message)
                }
            } catch (Exception: Exception) {
                println(Exception)
            }

        }
    }

    //search
    private val _searchResponse = MutableStateFlow<SearchResponse?>(null)
    val searchResponse: StateFlow<SearchResponse?> = _searchResponse

    fun getSearchData(token: String, lang: String, query: String) {
        viewModelScope.launch {
            _searchResponse.value = null
            try {
                val response = RetrofitInstance.apiService.searchProducts(
                    token,
                    lang,
                    SearchRequest(query)
                )
                if (response.isSuccessful) {
                    _searchResponse.value = response.body()
                } else {
                    println("Error: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                println("Exception: ${e.localizedMessage}")
            }
        }
    }

    fun clearSearchData() {
        _searchResponse.value = null
    }

    private val _selectedItem = MutableStateFlow(0)
    val selectedItem: StateFlow<Int> = _selectedItem

    fun selectItem(index: Int) {
        _selectedItem.value = index
    }

    private val _favouriteStates = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val favouriteStates: StateFlow<Map<Int, Boolean>> get() = _favouriteStates

    fun toggleFavourite(productId: Int) {
        val currentFavorites = _favouriteStates.value.toMutableMap()
        val isFavourite = currentFavorites[productId] ?: false
        currentFavorites[productId] = !isFavourite // Toggle favorite status
        _favouriteStates.value = currentFavorites
    }


}