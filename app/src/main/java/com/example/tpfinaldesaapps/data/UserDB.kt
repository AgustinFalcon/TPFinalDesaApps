package com.example.exampletpseminario.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tpfinaldesaapps.TpDesaMobileApp
import com.example.tpfinaldesaapps.constants.Constants
import com.example.tpfinaldesaapps.data.UserDao
import com.example.tpfinaldesaapps.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDB? = null

        fun getDatabase(): UserDB {
            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    TpDesaMobileApp.instance.applicationContext,
                    UserDB::class.java,
                    Constants.DB_NAME
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }

}