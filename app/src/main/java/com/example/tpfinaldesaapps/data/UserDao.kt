package com.example.tpfinaldesaapps.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tpfinaldesaapps.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(user: User)


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Update
    fun update(user: User)
}