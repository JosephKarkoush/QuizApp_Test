package com.example.quizapp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

const val BASE_ENDPOINT_URL = "http://rigel.student.hig.se/gmc/webservice/"

class StatementRepository {
    private val retrofit : Retrofit by lazy {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        Retrofit.Builder().baseUrl(BASE_ENDPOINT_URL).addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    private val statementApi: StatementApi by lazy{
        retrofit.create(StatementApi::class.java)
    }

    suspend fun getStatements(): List<Statement>{
        val response = statementApi.getStatements("all")
        return if(response.isSuccessful)
            response.body() ?: emptyList()
        else
            emptyList()
    }
}