package com.example.tpfinaldesaapps.iu.add

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tpfinaldesaapps.R
import com.example.tpfinaldesaapps.model.UserExample
import com.example.tpfinaldesaapps.databinding.FragmentAddBinding
import com.example.tpfinaldesaapps.model.User
import com.example.tpfinaldesaapps.viewModel.UserViewModel


class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)


        binding.btnAddUser.setOnClickListener {

            //Obtengo los datos de la ui
            val name = binding.etName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val age = binding.etAge.text.toString()

            if (name.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {

                // Creo el objeto
                val user = User(id = 0, name = name, lastName = lastName, age = age.toInt())

                // Se lo paso al view model
                userViewModel.insertUser(user = user)
                Log.d("AddFragment", "usuario creado! $user")

                // Navego al listado
                findNavController().navigate(R.id.action_addFragment_to_listFragment)

            } else {
                Toast.makeText(requireContext(), "Complete todos los campos!", Toast.LENGTH_SHORT).show()
            }

        }

        return binding.root
    }


}