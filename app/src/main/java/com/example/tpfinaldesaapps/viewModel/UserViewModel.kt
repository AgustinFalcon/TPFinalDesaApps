package com.example.tpfinaldesaapps.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.tpfinaldesaapps.model.User
import com.example.tpfinaldesaapps.repository.UserRepository


class UserViewModel() : ViewModel() {

    private val repository = UserRepository()

    val readAllData: LiveData<List<User>> = repository.readAllData


    fun insertUser(user: User) {
        repository.insertUser(user = user)
    }


    fun updateUser(user: User) {
        repository.updateUser(user = user)
    }



}