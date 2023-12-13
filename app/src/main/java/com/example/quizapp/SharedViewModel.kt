package com.example.quizapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.quizapp.data.Statement
import com.example.quizapp.data.StatementRepository

class SharedViewModel(app: Application) : AndroidViewModel(app) {

    val qRepository:StatementRepository=StatementRepository()
    val statements : LiveData<List<Statement>> = liveData{
        val data = qRepository.getStatements()
        emit(data)
    }

    }