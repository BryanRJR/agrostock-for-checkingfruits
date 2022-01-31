package com.argostock.capstoneapp.camera.remote.network

import com.argostock.capstoneapp.camera.remote.response.AgroResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("detect")
    fun uploadImage(
        @Part image: MultipartBody.Part
    ): Call<AgroResponse>
}