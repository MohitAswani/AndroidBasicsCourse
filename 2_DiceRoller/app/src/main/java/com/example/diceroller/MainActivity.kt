package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.security.SecureRandom
import kotlin.random.Random

const val FIRST_DICE="first_dice_value"
const val SECOND_DICE="second_dice_value"


class MainActivity : AppCompatActivity() {
    lateinit var diceImage : ImageView
    var diceImageval : Int=0
    lateinit var diceImage1 : ImageView
    var diceImageval1 : Int=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage=findViewById(R.id.dice_image)
        diceImage1=findViewById(R.id.dice_image_1)
        if(savedInstanceState!=null)
        {
            diceImageval=savedInstanceState.getInt(FIRST_DICE,0)
            diceImageval1=savedInstanceState.getInt(SECOND_DICE,0)
            setDiceImages()
        }
        val rollButton: Button=findViewById(R.id.roll_button)
        rollButton.setOnClickListener{rollDice()}
        val resetButton:Button=findViewById(R.id.reset_button)
        resetButton.setOnClickListener{reset()}
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(FIRST_DICE,diceImageval)
        outState.putInt(SECOND_DICE,diceImageval1)
    }
    private fun setDiceImages(){
        val drawableResource=when(diceImageval)
        {
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        val drawableResource1=when(diceImageval1)
        {
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        diceImage1.setImageResource(drawableResource1)
    }
    private fun rollDice(){
//        Toast.makeText(this,"button clicked",Toast.LENGTH_SHORT).show()
        diceImageval=Random.nextInt(555554)%6+1
        val drawableResource=when(diceImageval)
        {
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        diceImage.setImageResource(drawableResource)
        diceImageval1=Random.nextInt(1000000009)%6+1
        val drawableResource1=when(diceImageval1)
        {
            1->R.drawable.dice_1
            2->R.drawable.dice_2
            3->R.drawable.dice_3
            4->R.drawable.dice_4
            5->R.drawable.dice_5
            else->R.drawable.dice_6
        }
        diceImage1.setImageResource(drawableResource1)
    }


    private fun reset(){
        diceImage.setImageResource(R.drawable.empty_dice)
        diceImage1.setImageResource(R.drawable.empty_dice)
    }
}