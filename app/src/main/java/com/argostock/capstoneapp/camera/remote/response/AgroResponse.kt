package com.argostock.capstoneapp.camera.remote.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AgroResponse (
    @field: SerializedName("file")
    @Expose
    val file: String? = null,
    @field: SerializedName("result")
    @Expose
    val result: String? = null,

)