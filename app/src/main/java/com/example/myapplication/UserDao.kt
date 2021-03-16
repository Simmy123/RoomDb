package com.example.myapplication

import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Query("delete from User where id = :userId")
    fun delete(userId: String): Int

    @Query("delete from User")
    fun deleteAllUsers()

    @Query("select * from User")
    fun getAllUsers(): List<User>
}