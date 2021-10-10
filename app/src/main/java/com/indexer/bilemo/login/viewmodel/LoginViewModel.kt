package com.indexer.bilemo.login.viewmodel

import android.content.Context
import android.text.Editable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indexer.bilemo.R
import com.indexer.bilemo.base.DataStoreRepository
import com.indexer.bilemo.login.entities.LoginUser
import com.indexer.bilemo.login.model.LoginUserUI
import com.indexer.bilemo.login.usecase.GetAllLoginUseCase
import com.indexer.bilemo.utils.IS_REMEMBER
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUserUseCase: GetAllLoginUseCase,
    private val dataStoreRepository: DataStoreRepository
) :
    ViewModel() {
    var userName = MutableLiveData<String>()
    var userPassword = MutableLiveData<String>()
    var userCountry = MutableLiveData<String>()
    var remeberUserPassword = MutableLiveData<Boolean>()
    private var userMutableLiveData: MutableLiveData<LoginUserUI>? = null


    val user: MutableLiveData<LoginUserUI>
        get() {
            when (userMutableLiveData) {
                null -> {
                    userMutableLiveData = MutableLiveData()
                }
            }
            return userMutableLiveData as MutableLiveData<LoginUserUI>
        }

    fun getCountriesForAdapter(context: Context): Array<out String> {
        return context.resources.getStringArray(R.array.countries)
    }

    fun saveRememberState(boolean: Boolean) {
        viewModelScope.launch {
            dataStoreRepository.putBoolean(IS_REMEMBER, boolean)
        }
    }


    fun getLoginState(): Boolean? = runBlocking {
        dataStoreRepository.getBoolean(IS_REMEMBER)
    }

    suspend fun getLoginUser(loginUser: LoginUser): Boolean {
        val user = loginUserUseCase.getAllLoginUser(loginUser)
        return user.isNotEmpty()
    }


    private fun updateUserData() {
        val loginUser = LoginUserUI(
            userName.value,
            userPassword.value, userCountry.value, isRememberLoginState = remeberUserPassword.value
        )
        userMutableLiveData?.value = loginUser
    }

    fun doOnDropDownChange(text: Editable) {
        userCountry.value = text.toString()
    }

    fun onUserNameChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isNotEmpty()) {
            userName.value = s.toString()
            updateUserData()
        }
    }

    fun onCheckChange(boolean: Boolean) {
        remeberUserPassword.value = boolean
        updateUserData()
    }

    fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        if (s.isNotEmpty() && s.toString().length > 1) {
            userPassword.value = s.toString()
            updateUserData()
        }
    }

}