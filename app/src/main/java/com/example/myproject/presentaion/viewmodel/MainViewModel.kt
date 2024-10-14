package com.example.myproject.presentaion.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myproject.data.model.CategoriesRequest
import com.example.myproject.data.model.HomeResponse
import com.example.myproject.data.model.LoginRequest
import com.example.myproject.data.model.LoginResponse
import com.example.myproject.data.model.RegisterRequest
import com.example.myproject.data.model.RegisterResponse
import com.example.myproject.data.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> get() = _loginResponse

    //    login
    fun login(loginRequest: LoginRequest, onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.login(loginRequest)
                if (response.isSuccessful) {
                    _loginResponse.value = response.body()
                    onSuccess(response.body()?.data?.token ?: "")
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


    private val _categoryResponse = MutableStateFlow<CategoriesRequest?>(null)
    val categoryResponse: StateFlow<CategoriesRequest?> get() = _categoryResponse

    //get category
    fun getCategory(token: String, lang: String) {
        viewModelScope.launch {
            try {
                val response =
                    RetrofitInstance.apiService.getCategories(token, lang)
                if (response.isSuccessful) {
                    _categoryResponse.value = response.body()
                }
            } catch (Exception: Exception) {
                println(Exception)
            }
        }
    }


}