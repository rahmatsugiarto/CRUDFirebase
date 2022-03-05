package com.binar.crudfirebase.ui.fragment.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.binar.crudfirebase.databinding.ItemNoteBinding
import com.binar.crudfirebase.model.Note

class HomeListAdapter :
    RecyclerView.Adapter<HomeListAdapter.ListVeiwHolder>() {
    private var oldListNote = ArrayList<Note>()

    fun setData(newlist: ArrayList<Note>) {
        val diffUtil = ListNoteDiffUtil(oldListNote, newlist)
        val diffResult = DiffUtil.calculateDiff(diffUtil)
        oldListNote = newlist
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ListVeiwHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvDesc.text = note.desc
            binding.tvDate.text = note.date
            binding.root.setOnClickListener {
                val nav = HomeFragmentDirections.actionHomeFragmentToEditFragment(note)
                nav.note = note
                binding.root.findNavController().navigate(nav)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVeiwHolder {
        val binding =
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ListVeiwHolder(binding)
    }

    override fun onBindViewHolder(holder: ListVeiwHolder, position: Int) {
        val note = oldListNote[position]
        holder.bind(note)
    }

    override fun getItemCount(): Int = oldListNote.size
}
