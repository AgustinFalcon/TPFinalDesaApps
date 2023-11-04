package com.example.tpfinaldesaapps.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tpfinaldesaapps.model.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(user: User)


    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>> //getall

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()
}