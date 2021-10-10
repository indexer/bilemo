package com.indexer.bilemo.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indexer.bilemo.user.enitity.UserResponse
import com.indexer.bilemo.user.usecase.GetUserCaseById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserCaseById: GetUserCaseById,
) : ViewModel() {
    var getUserById = MutableLiveData<UserResponse>()

    fun getUserById(userId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            getUserCaseById.getUserFromDatabase(userId).collect {
                getUserById.value = it
            }
        }
    }
}