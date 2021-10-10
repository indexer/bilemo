package com.indexer.bilemo.splash.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indexer.bilemo.login.entities.LoginUser
import com.indexer.bilemo.splash.usecase.InsertLoginUseCase
import com.indexer.bilemo.utils.getHashFromPasswordString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val insertLoginUseCase: InsertLoginUseCase,
) : ViewModel() {

    fun insertLoginUser(loginUser: LoginUser) {
        viewModelScope.launch(Dispatchers.IO) {
            loginUser.userPassword = getHashFromPasswordString(loginUser.userPassword)
            insertLoginUseCase.insertUsers(listOf(loginUser))
        }
    }
}