package com.example.userlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlist.common.Resource
import com.example.userlist.model.User
import com.example.userlist.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UsersListViewModel @Inject constructor(private val usersRepository: UsersRepository) :
    ViewModel() {

    private val _usersList: MutableList<User> = mutableListOf()
    private val _stateFlow: MutableStateFlow<Resource<List<User>>> =
        MutableStateFlow(Resource.Loading)
    val stateFlow: StateFlow<Resource<List<User>>> = _stateFlow.asStateFlow()

    private var currentPage = 0
    private val maxPages = 2

    init {
        getUserList()
    }


    private fun getUserList() {
        viewModelScope.launch {
            val users = usersRepository.getUsers(currentPage)
            if (users is Resource.Success) {
                _usersList.addAll(users.data)
                _stateFlow.value = Resource.Success(_usersList.toList())
            } else {
                _stateFlow.value = users
            }

        }
    }

    fun loadNextPage() {
        if (currentPage < maxPages) {
            currentPage++
            getUserList()
        }
    }
}