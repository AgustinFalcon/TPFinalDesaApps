package com.example.tpfinaldesaapps.iu

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinaldesaapps.R
import com.example.tpfinaldesaapps.model.UserExample
import com.example.tpfinaldesaapps.databinding.ItemRecyclerviewUserBinding
import com.example.tpfinaldesaapps.model.User


class UserAdapter(): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList = emptyList<User>()

    inner class UserViewHolder(private val binding: ItemRecyclerviewUserBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            with(binding) {
                tvId.text = user.id.toString()
                tvName.text = user.name
                tvLastName.text = user.lastName
                tvAge.text = user.age.toString()

                root.setOnClickListener {

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


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemRecyclerviewUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user = userList.get(position)
        holder.bind(user = user)
        //holder.itemView.findNavController()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    //override fun getItemCount(): Int = userList.size


    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: List<User>) {
        userList = users
        notifyDataSetChanged()
    }


}