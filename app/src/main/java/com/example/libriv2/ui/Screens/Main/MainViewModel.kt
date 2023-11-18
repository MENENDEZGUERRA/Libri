package com.example.libriv2.ui.Screens.Main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class MainViewModel: ViewModel()  {
    val state = mutableStateOf(Users())

    init {
        getData()
    }

    private fun getData(){
        viewModelScope.launch {
            state.value = getDataFromFirestore()
        }
    }
}
suspend fun getDataFromFirestore():Users{
    val db = FirebaseFirestore.getInstance()
    var users = Users()

    try{
        db.collection("users").get().await().map {
            val result = it.toObject(users::class.java)
            users = result
        }
    }catch (e:FirebaseFirestoreException){
        Log.d("error", "getDataFromFirestore: $e")
    }
    return users
}
