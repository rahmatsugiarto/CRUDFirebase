package com.binar.crudfirebase.ui.fragment.edit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.binar.crudfirebase.model.Note
import com.binar.crudfirebase.util.CurrentDate.getCurreentDateTimeString
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class EditViewModel : ViewModel() {
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status

    private val db = Firebase.firestore

    fun update(id: String, title: String, desc: String) {
        val dataUpdate = Note(id, title, desc, getCurreentDateTimeString())
        viewModelScope.launch {
            db.collection("Notes").document(id)
                .set(dataUpdate)
                .addOnSuccessListener {
                    _status.value = "Update Succes"
                }
                .addOnFailureListener { e ->
                    _status.value = "hmmm... Someting wrong in here"
                    Log.w("addnote", "Error adding document", e)
                }
        }
    }

    fun delete(id: String) {
        viewModelScope.launch {
            db.collection("Notes").document(id)
                .delete()
                .addOnSuccessListener {
                    _status.value = "Delete Succes"
                }
                .addOnFailureListener { e ->
                    _status.value = "hmmm... Someting wrong in here"
                    Log.w("delete", "Error deleting document", e)
                }
        }
    }
}