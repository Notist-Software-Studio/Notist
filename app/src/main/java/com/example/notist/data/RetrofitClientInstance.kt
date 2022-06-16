package com.example.notist.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
//make the connection to the endpoints
object RetrofitClientInstance {
    private var retrofit: Retrofit? = null
    private val BASE_URL = "https://62986d6ede3d7eea3c66fc6d.mockapi.io/api/"

    val retrofitInstance : Retrofit?
    get() {
        if(retrofit==null){
            retrofit = retrofit2.Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }
}