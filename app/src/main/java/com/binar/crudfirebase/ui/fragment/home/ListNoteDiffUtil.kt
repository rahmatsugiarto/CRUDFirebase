package com.binar.crudfirebase.ui.fragment.home

import androidx.recyclerview.widget.DiffUtil
import com.binar.crudfirebase.model.Note

class ListNoteDiffUtil(private val oldList: List<Note>, private val newList: List<Note>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList == newList
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].id != newList[newItemPosition].id -> {
                false
            }
            oldList[oldItemPosition].title != newList[newItemPosition].title -> {
                false
            }
            oldList[oldItemPosition].desc != newList[newItemPosition].desc -> {
                false
            }
            oldList[oldItemPosition].date != newList[newItemPosition].date -> {
                false
            }
            else -> true
        }
    }
}