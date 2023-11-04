package com.example.tpfinaldesaapps.iu

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tpfinaldesaapps.R
import com.example.tpfinaldesaapps.model.UserExample
import com.example.tpfinaldesaapps.databinding.FragmentListBinding
import com.example.tpfinaldesaapps.viewModel.UserViewModel


class ListFragment : Fragment(), MenuProvider {

    private lateinit var binding: FragmentListBinding

    private val userViewModel by viewModels<UserViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)


        val adapter = UserAdapter()
        binding.recyclerViewUser.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewUser.adapter = adapter


        // Linea divisoria
        val divider = DividerItemDecoration(requireContext(), LinearLayoutManager(requireContext()).orientation)
        binding.recyclerViewUser.addItemDecoration(divider)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)



        binding.btnAdd.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        userViewModel.readAllData.observe(viewLifecycleOwner) { userList ->
            adapter.setList(users = userList)
        }



        return binding.root
    }

    private fun deleteAll() {
        val dialog = AlertDialog.Builder(requireContext())

        dialog.setTitle("¿Eliminar Todos?")
        dialog.setMessage("¿Esta seguro que desea eliminar a todos los usuarios?")

        dialog.setNegativeButton("No") { _,_ ->
            return@setNegativeButton
        }

        dialog.setPositiveButton("Yes") { _,_ ->
            userViewModel.deleteAllUsers()
        }

        dialog.create().show()
    }


    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when(menuItem.itemId) {
            R.id.menu_delete -> {
                deleteAll()
                true
            }

            else -> {
                false
            }
        }
    }


}