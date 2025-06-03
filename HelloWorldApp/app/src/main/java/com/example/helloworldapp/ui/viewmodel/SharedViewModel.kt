package com.example.helloworldapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {

    private val _user1Prefs = MutableStateFlow<List<String>>(emptyList())
    val user1Prefs: StateFlow<List<String>> = _user1Prefs

    private val _user2Prefs = MutableStateFlow<List<String>>(emptyList())
    val user2Prefs: StateFlow<List<String>> = _user2Prefs

    fun setUser1Prefs(prefs: List<String>) {
        _user1Prefs.value = prefs
    }

    fun setUser2Prefs(prefs: List<String>) {
        _user2Prefs.value = prefs
    }
}