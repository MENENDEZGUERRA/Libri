package com.example.libriv2.ui.Screens.Main

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class MainViewModel: ViewModel()  {
    val state = mutableStateOf(Users())

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            val authUser = FirebaseAuth.getInstance().currentUser
            val displayName = authUser?.email?.substringBefore('@') ?: ""
            state.value = Users(displayName = displayName)
        }
    }



}