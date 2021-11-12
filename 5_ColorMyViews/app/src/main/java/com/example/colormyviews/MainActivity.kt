package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners(){
        val boxOne=findViewById<TextView>(R.id.box_one_text)
        val boxTwo=findViewById<TextView>(R.id.box_two_text)
        val boxThree=findViewById<TextView>(R.id.box_three_text)
        val boxFour=findViewById<TextView>(R.id.box_four_text)
        val boxFive=findViewById<TextView>(R.id.box_five_text)

        val redButton=findViewById<View>(R.id.red_button)
        val yellowButton=findViewById<View>(R.id.yellow_button)
        val greenButton=findViewById<View>(R.id.green_button)

        val rootConstraintLayout=findViewById<View>(R.id.constraint_layout)

        val clickableView= listOf<View>(boxOne,boxTwo,boxThree,boxFour,boxFive,redButton,yellowButton,greenButton,rootConstraintLayout)

        for(item in clickableView)
            item.setOnClickListener{makeColored(it)}
    }
    private fun makeColored(view : View)
    {
        when(view.id)
        {
            R.id.box_one_text->view.setBackgroundColor(Color.BLUE)
            R.id.box_two_text->view.setBackgroundColor(Color.CYAN)
            R.id.box_three_text->view.setBackgroundColor(Color.MAGENTA)
            R.id.box_four_text->view.setBackgroundColor(Color.GREEN)
            R.id.box_five_text->view.setBackgroundColor(Color.DKGRAY)
            R.id.red_button->findViewById<View>(R.id.box_three_text).setBackgroundColor(Color.RED)
            R.id.yellow_button->findViewById<View>(R.id.box_four_text).setBackgroundColor(Color.YELLOW)
            R.id.green_button->findViewById<View>(R.id.box_five_text).setBackgroundColor(Color.GREEN)
            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}