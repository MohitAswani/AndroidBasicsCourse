package com.example.myguesstheword.game

import android.database.DatabaseUtils
import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class GameViewModel : ViewModel() {

    private val timer:CountDownTimer

    companion object{
        private const val DONE=0L

        private const val ONE_SECOND=1000L

        private const val COUNTDOWN_TIME=60000L
    }
    private var _word=MutableLiveData<String>()
    val word:LiveData<String>
    get() = _word

    private var _score=MutableLiveData<Int>()
    val score:LiveData<Int>
    get() = _score

    private var _gameFinished=MutableLiveData<Boolean>()
    val gameFinished:LiveData<Boolean>
    get()= _gameFinished

    private var _currentTime=MutableLiveData<Long>()
    val currentTime:LiveData<Long>
    get() = _currentTime

    val currentTimeString=Transformations.map(currentTime){time->
        DateUtils.formatElapsedTime(time)
    }

    val guess=Transformations.map(word){ word->
        val random=(1..word.length).random()
        "Length of the word is ${word.length} and the ${random} is ${word.get(random-1).toUpperCase()}"
    }

    private lateinit var wordList: MutableList<String>


    init {
        Log.i("GameViewModel","GameViewModel created")
        resetList()
        nextWord()

        timer=object :CountDownTimer(COUNTDOWN_TIME, ONE_SECOND){
            override fun onTick(millisUntilFinished: Long)
            {
                _currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinished()
            }
        }

        timer.start()
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
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
            resetList()
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
