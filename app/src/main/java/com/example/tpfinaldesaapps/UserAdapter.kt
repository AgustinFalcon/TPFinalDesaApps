package com.example.tpfinaldesaapps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinaldesaapps.databinding.ItemRecyclerviewUserBinding


class UserAdapter(private val userList: MutableList<User>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    inner class UserViewHolder(private val binding: ItemRecyclerviewUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                tvId.text = user.id.toString()
                tvName.text = user.name
                tvLastName.text = user.lastName
                tvAge.text = user.age.toString()

                root.setOnClickListener {
                    Log.d("UserAdapter", "el usuario es: $user")
                    val bundle = Bundle()
                    bundle.putSerializable("user", user)
                    itemView.findNavController().navigate(R.id.action_listFragment_to_updateFragment, bundle)
                }
            }
//            binding.tvId.text = user.id.toString()
//            binding.tvName.text = user.name
//            binding.tvLastName.text = user.lastName
//            binding.tvAge.text = user.age.toString()
//            binding.root.setOnClickListener {  }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.UserViewHolder {
        val binding = ItemRecyclerviewUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: UserAdapter.UserViewHolder, position: Int) {
        val user = userList.get(position)
        holder.bind(user = user)
        //holder.itemView.findNavController()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    //override fun getItemCount(): Int = userList.size



}