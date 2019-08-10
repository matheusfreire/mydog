package com.msf.mydog.model

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("_id") val _id : String,
                @SerializedName("email") val email : String,
                @SerializedName("token") val token : String,
                @SerializedName("createdAt") val createdAt : String,
                @SerializedName("updatedAt") val updatedAt : String,
                @SerializedName("__v") val __v : Int)