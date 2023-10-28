package com.example.tpfinaldesaapps.model

import java.io.Serializable

data class UserExample(
    val id:Int,
    val name: String,
    val lastName: String,
    val age: Int
) : Serializable
