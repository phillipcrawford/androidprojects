package com.example.criminaintent

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log


private const val TAG = "CrimeListFragment"

class CrimeListFragment : Fragment() {

    private val crimeListViewModel: CrimeListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "Total crimes: ${crimeListViewModel.crimes.size}")
    }
}