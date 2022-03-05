package com.binar.crudfirebase.ui.fragment.add


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


class AddViewModel : ViewModel() {
    val inputTitle = MutableLiveData<String>()
    val inputDesc = MutableLiveData<String>()
    private val _status = MutableLiveData<String>()
    val status: LiveData<String> = _status
    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean> = _success
//text watcher
//    private val textObserver = Observer<String> { onTextChanged(it) }
//
//    init {
//        inputTitle.observeForever(textObserver)
//        inputDesc.observeForever(textObserver)
//    }

    private val db = Firebase.firestore

    fun add() {
        val id = db.collection("Notes").document().id
        val data = Note(
            id,
            inputTitle.value.toString(),
            inputDesc.value.toString(),
            getCurreentDateTimeString()
        )
        viewModelScope.launch {
            db.collection("Notes").document(id)
                .set(data)
                .addOnSuccessListener {
                    _status.value = "Saved"
                    _success.value = false
                }
                .addOnFailureListener { e ->
                    _status.value = "hmmm... Someting wrong in here"
                    Log.d("addnote", "Error adding document", e)
                }
        }
    }
    //text watcher
//    override fun onCleared() {
//        inputTitle.removeObserver(textObserver)
//        inputDesc.removeObserver(textObserver)
//    }
//
//    private fun onTextChanged(s: String) {
//        _success.value = true
//    }
}