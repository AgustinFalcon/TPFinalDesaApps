package com.example.tpfinaldesaapps.iu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tpfinaldesaapps.R
import com.example.tpfinaldesaapps.User
import com.example.tpfinaldesaapps.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val bundle = arguments?.getSerializable("user") as User

        binding.btnUpdateUser.setOnClickListener {

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }


        return binding.root
    }


}