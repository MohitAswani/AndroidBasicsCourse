package com.example.myguesstheword.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(private val finalScore:Int):ViewModel() {
    private val _score= MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    private val _playAgain=MutableLiveData<Boolean>()
    val playAgain:LiveData<Boolean>
    get() = _playAgain

    init {
        Log.i("ScoreViewModel","The score is $finalScore")
        _score.value=finalScore
    }

    fun onPlayAgain(){
        _playAgain.value=true
    }
    fun onPlayAgainComplete(){
        _playAgain.value=false
    }

    override fun onCleared() {
        Log.i("ScoreViewModel","ScoreViewModel destroyed")
    }
}