package com.example.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myName=MyName("Mohit Aswani")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.myName = myName
        val doneButton=binding.doneButton
        doneButton.setOnClickListener{addNickname(it)}
        val nicknameTextView=binding.nicknameText
        nicknameTextView.setOnClickListener{updateNickname()}
    }
    private fun addNickname(view:View) {
        binding.apply {
            val editText=binding.personNameEdit
            myName?.nickname = personNameEdit.text.toString()
            invalidateAll()
            personNameEdit.visibility = View.GONE
            doneButton.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun updateNickname()
    {
        val editText=binding.personNameEdit
        val doneButton=binding.doneButton
        editText.visibility=View.VISIBLE
        doneButton.visibility=View.VISIBLE
        binding.nicknameText.visibility=View.GONE
        editText.requestFocus()
        val imm=getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(editText,0)
    }
}