package com.indexer.bilemo.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.indexer.bilemo.user.enitity.UserResponse
import com.indexer.bilemo.user.usecase.GetAllLUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val getAllLUserUseCase: GetAllLUserUseCase,
) : ViewModel() {
    var userList = MutableLiveData<List<UserResponse>>()


    fun saveRememberState(boolean: Boolean) {
        viewModelScope.launch {
            getAllLUserUseCase.saveRememberState(boolean)
        }
    }

    fun getAllUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllLUserUseCase.getAllUserFromApi().collect {
                when {
                    it.valueOrNull != null -> {
                        viewModelScope.launch(Dispatchers.Main) {
                            getAllLUserUseCase.getUserFromDatabase().collect { it ->
                                userList.value = it
                            }
                        }
                    }
                }
            }
        }
    }
}