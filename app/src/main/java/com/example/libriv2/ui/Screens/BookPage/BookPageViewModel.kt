package com.example.libriv2.ui.Screens.BookPage

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.libriv2.ui.Screens.Main.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class BookPageViewModel : ViewModel() {
    val bookState = mutableStateOf(Books())

    fun getBookData(bookId: String) {
        viewModelScope.launch {
            bookState.value = getBookFromFirestore(bookId)
        }
    }

    private suspend fun getBookFromFirestore(bookId: String): Books {
        val db = FirebaseFirestore.getInstance()
        return try {
            val document = db.collection("books").document(bookId).get().await()
            document.toObject(Books::class.java) ?: Books()
        } catch (e: Exception) {
            Log.e("BookViewModel", "Error getting book data", e)
            Books()
        }
    }
}
