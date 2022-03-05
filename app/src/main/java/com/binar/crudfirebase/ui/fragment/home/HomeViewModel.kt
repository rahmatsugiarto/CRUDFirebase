package com.binar.crudfirebase.ui.fragment.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.crudfirebase.model.Note
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _listNote = mutableListOf<Note>()
    private val listnote get() = _listNote

    private val _listNoteLive = MutableLiveData<List<Note>>()
    val listNoteLIve: LiveData<List<Note>> get() = _listNoteLive
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val db = Firebase.firestore

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            db.collection("Notes")
                .get()
                .addOnSuccessListener { result ->
                    listnote.removeAll(listnote)
                    for (document in result) {
                        val note = Note(
                            document.getString("id"),
                            document.getString("title"),
                            document.getString("desc"),
                            document.getString("date")
                        )
                        _listNote.add(note)
                        Log.d("getnote", "suksesss")
                    }
                    _listNoteLive.value = sortDates(listnote)
                }
                .addOnFailureListener { exception ->
                    _status.value = exception.toString()
                    Log.w("getnote", "Error getting documents.", exception)
                }
        }
    }


    private fun sortDates(notes: MutableList<Note>): MutableList<Note> {
        notes.sortByDescending { it.date }
        return notes
    }
}