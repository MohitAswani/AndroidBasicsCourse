package com.example.myguesstheword.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myguesstheword.R
import com.example.myguesstheword.databinding.FragmentScoreBinding

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding:FragmentScoreBinding=DataBindingUtil.inflate(inflater,R.layout.fragment_score,container,false)

        viewModelFactory= ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)

        viewModel=ViewModelProvider(this,viewModelFactory).get(ScoreViewModel::class.java)

        binding.lifecycleOwner=viewLifecycleOwner

        viewModel.playAgain.observe(viewLifecycleOwner, Observer { playAgain->
            if(playAgain)
            {
                findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToTitleFragment())
                viewModel.onPlayAgainComplete()
            }
        })
        binding.scoreViewModel=viewModel
        return binding.root
    }

}