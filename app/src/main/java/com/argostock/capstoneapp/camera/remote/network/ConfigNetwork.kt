package com.argostock.capstoneapp.camera.remote.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {
    companion object {
        fun getRetrofit() : ApiService {
            val retrofit = Retrofit.Builder()
                    // PUT API HERE (BUT WE DONT HAVE THAT)
                .baseUrl("http://34.123.126.251:5000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}