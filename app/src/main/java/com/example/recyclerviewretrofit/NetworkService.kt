package com.example.recyclerviewretrofit

import com.example.recyclerviewretrofit.news.Welcome
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    // before category added to query url was "v2/top-headlines?country=in&category=sports"
    @GET("v2/top-headlines?country=in")
    fun getNews(@Query("apiKey")key:String,@Query("category")category: String):Call<Welcome>

    companion object{
        operator fun invoke():NetworkService{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://newsapi.org/")
                .build().create(NetworkService::class.java)
        }
    }
}

