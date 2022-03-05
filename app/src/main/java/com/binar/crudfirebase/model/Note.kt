package com.binar.crudfirebase.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    var id: String?,
    var title: String?,
    var desc: String?,
    var date: String?
) : Parcelable
