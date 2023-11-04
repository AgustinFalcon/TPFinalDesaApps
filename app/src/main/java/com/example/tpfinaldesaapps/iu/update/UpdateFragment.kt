package com.example.tpfinaldesaapps.iu.update

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import com.example.tpfinaldesaapps.R
import com.example.tpfinaldesaapps.model.UserExample
import com.example.tpfinaldesaapps.databinding.FragmentUpdateBinding
import com.example.tpfinaldesaapps.model.User
import com.example.tpfinaldesaapps.viewModel.UserViewModel


class UpdateFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentUpdateBinding
    private val userViewModel by viewModels<UserViewModel>()

    private var user: User? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        user = arguments?.getSerializable("user") as User // Bundle

        binding.etName.setText(user?.let { it.name })
        binding.etLastName.setText(user!!.lastName)
        binding.etAge.setText(user!!.age.toString())

        binding.btnUpdateUser.setOnClickListener {
            validateFields(user!!)
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)
        /*menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                TODO("Not yet implemented")
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                TODO("Not yet implemented")
            }
        },viewLifecycleOwner, Lifecycle.State.RESUMED)*/




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

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteUser()
                true
            }

            else -> {
                false
            }
        }
    }

    private fun deleteUser() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("¿Desea Eliminar?")
        dialog.setMessage("Esta seguro que desea eliminar ha ${user!!.name}")

        dialog.setNegativeButton(getString(R.string.no_option)) { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteUser(user = user!!)
            Toast.makeText(requireContext(), "User eliminado!", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        dialog.create().show()
    }


}