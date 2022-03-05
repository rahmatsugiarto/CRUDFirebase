package com.binar.crudfirebase.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.binar.crudfirebase.R
import com.binar.crudfirebase.databinding.FragmentHomeBinding
import com.binar.crudfirebase.model.Note

@SuppressLint("NotifyDataSetChanged")
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvNote: RecyclerView
    private val viewModel by viewModels<HomeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
        }
        rvNote = binding.rvNote
        rvNote.setHasFixedSize(true)

        viewModel.status.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        viewModel.listNoteLIve.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                rvNote.visibility = View.GONE
                binding.layoutNoNotes.visibility = View.VISIBLE
            } else {
                binding.layoutNoNotes.visibility = View.GONE
                rvNote.visibility = View.VISIBLE
                showList(it)
            }
        }

        binding.btnAdd.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showList(list: List<Note>) {
        val listNoteAdapter = HomeListAdapter()
        rvNote.layoutManager = LinearLayoutManager(requireContext())
        rvNote.adapter = listNoteAdapter
        listNoteAdapter.setData(list as ArrayList<Note>)
    }
}
