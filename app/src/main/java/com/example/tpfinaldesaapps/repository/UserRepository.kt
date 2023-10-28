package com.example.tpfinaldesaapps.repository

import androidx.lifecycle.LiveData
import com.example.exampletpseminario.data.UserDB
import com.example.tpfinaldesaapps.model.User


class UserRepository {


    private val userDao = UserDB.getDatabase().userDao()

    val readAllData: LiveData<List<User>> = userDao.readAllData()


    fun insertUser(user: User) {
        userDao.insert(user = user)
    }


    fun updateUser(user: User) {
        userDao.update(user = user)
    }

}