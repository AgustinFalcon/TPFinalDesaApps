package com.example.tpfinaldesaapps.iu.update

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.tpfinaldesaapps.R
import com.example.tpfinaldesaapps.model.UserExample
import com.example.tpfinaldesaapps.databinding.FragmentUpdateBinding
import com.example.tpfinaldesaapps.model.User
import com.example.tpfinaldesaapps.viewModel.UserViewModel


class UpdateFragment : Fragment() {

    private lateinit var binding: FragmentUpdateBinding
    private val userViewModel by viewModels<UserViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        val user = arguments?.getSerializable("user") as User // Bundle

        binding.etName.setText(user.name)
        binding.etLastName.setText(user.lastName)
        binding.etAge.setText(user.age.toString())

        binding.btnUpdateUser.setOnClickListener {
            validateFields(user)
        }


        return binding.root
    }

    private fun validateFields(user: User) {
        val name = binding.etName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text.toString()

        if (name.isNotEmpty() && lastName.isNotEmpty() && age.isNotEmpty()) {

            //val user = User(id = user.id, name = name, lastName = lastName, age = age.toInt())
            val user = user.copy(name = name, lastName = lastName, age = age.toInt())
            userViewModel.updateUser(user = user)

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Complete todos los campos!", Toast.LENGTH_SHORT).show()
        }



    }


}