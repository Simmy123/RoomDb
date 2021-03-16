package com.example.myapplication

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var name: AppCompatEditText
    private lateinit var userId: AppCompatEditText
    private lateinit var saveButton: AppCompatButton
    private var userDatabase: UserDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDatabase = UserDatabase.getDatabaseInstance(this)
        name = findViewById<AppCompatEditText>(R.id.name_text)
        userId = findViewById<AppCompatEditText>(R.id.user_text)
        saveButton = findViewById<AppCompatButton>(R.id.save_button)
    }

    fun onClickSave(v: View) {
        AsyncTask.execute(Runnable {
            userDatabase?.userDao()
                ?.insert(User(userId.text.toString().toInt(), name.text.toString()))
        })
        val mToast = Toast.makeText(applicationContext, name.text, Toast.LENGTH_SHORT)
        mToast.show()
    }

    fun onClickDelete(v: View) {
        Completable.fromAction { userDatabase?.userDao()?.delete(userId.text.toString()) }
            .subscribeOn(Schedulers.io())
            .subscribe()
        val mToast = Toast.makeText(applicationContext, name.text, Toast.LENGTH_SHORT)
        mToast.show()
    }
}