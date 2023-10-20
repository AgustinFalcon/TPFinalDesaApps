package com.example.tpfinaldesaapps.iu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpfinaldesaapps.R
import com.example.tpfinaldesaapps.User
import com.example.tpfinaldesaapps.UserAdapter
import com.example.tpfinaldesaapps.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = UserAdapter(userList = mutableListOf(User(1, "Pepe", "Mujica", 86), User(2, "Leo", "Messi", 36)))
        binding.recyclerViewUser.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUser.adapter = adapter


        // Linea divisoria
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.recyclerViewUser.addItemDecoration(divider)



        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }

        return binding.root
    }



}