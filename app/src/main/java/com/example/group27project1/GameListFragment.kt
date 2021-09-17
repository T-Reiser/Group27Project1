package com.example.group27project1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders

private const val TAG = "CrimeListFragment"

class GameListFragment : Fragment(){

    private val crimeListViewModel: GameListViewModel by lazy {
        ViewModelProviders.of(this).get(GameListViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.games.size}")
    }

    companion object {
        fun newInstance(): GameListFragment {
            return GameListFragment()
        }
    }


}