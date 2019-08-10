package com.msf.mydog.model

import com.google.gson.annotations.SerializedName

data class ResponseLogin(@SerializedName("user") val user : User? = null)