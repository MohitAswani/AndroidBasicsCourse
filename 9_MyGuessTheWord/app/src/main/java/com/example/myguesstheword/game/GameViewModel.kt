package com.example.myguesstheword.game

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    private var _word=MutableLiveData<String>()
    val word:LiveData<String>
    get() = _word

    private var _score=MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    private var _gameFinished=MutableLiveData<Boolean>()
    val gameFinished:LiveData<Boolean>
    get()= _gameFinished

    private lateinit var wordList: MutableList<String>


    init {
        Log.i("GameViewModel","GameViewModel created")
        resetList()
        nextWord()
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel","GameViewModel destroyed")
    }

    private fun resetList(){
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }

    private fun nextWord(){
        if(wordList.isEmpty())
        {
            _gameFinished.value=true
        }
        else
        {
            _word.value = wordList.removeAt(0)
        }
    }

    fun onSkip(){
        _score.value=_score.value?.minus(1)?:0
        nextWord()
    }

    fun onCorrect(){
        _score.value=_score.value?.plus(1)?:0
        nextWord()
    }

    fun onGameFinished(){
        _gameFinished.value=true
    }

    fun afterGameFinished(){
        _gameFinished.value=false
    }


}
