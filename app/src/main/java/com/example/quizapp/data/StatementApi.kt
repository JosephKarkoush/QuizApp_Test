package com.example.quizapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StatementApi {
    @GET("wsquiz")
    suspend fun getStatements(@Query("choice") choice :String): Response<List<Statement>>

}