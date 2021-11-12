package com.example.myguesstheword.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.myguesstheword.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private lateinit var binding:FragmentGameBinding

    private lateinit var viewModel: GameViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        // Inflate the layout for this fragment
        binding=FragmentGameBinding.inflate(inflater,container,false)
        Log.i("GameFragment","Called ViewModelProvider.get")
        viewModel=ViewModelProvider(this).get(GameViewModel::class.java)

        binding.gameViewModel=viewModel

        binding.lifecycleOwner=viewLifecycleOwner

        viewModel.gameFinished.observe(viewLifecycleOwner, Observer { gameFinished->
            if(gameFinished) gameOver()
        })
        return binding.root
    }

    private fun gameOver(){
        Toast.makeText(activity,"Game over!",Toast.LENGTH_SHORT).show()
        val action=GameFragmentDirections.actionGameFragmentToScoreFragment()
        action.score= viewModel.score.value?:0
        NavHostFragment.findNavController(this).navigate(action)
        viewModel.afterGameFinished()
    }

}