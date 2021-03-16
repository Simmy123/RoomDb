package com.example.myapplication

import android.content.Context
import android.service.autofill.UserData
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class], exportSchema = false, version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        private var databaseInstance : UserDatabase? = null
        @Synchronized
        fun getDatabaseInstance(context: Context): UserDatabase {
            return if (databaseInstance == null){
                Room.databaseBuilder(context, UserDatabase::class.java, "user_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            else
                databaseInstance!!
        }
    }
}