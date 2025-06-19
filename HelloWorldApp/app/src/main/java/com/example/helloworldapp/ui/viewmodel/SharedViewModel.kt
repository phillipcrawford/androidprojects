package com.example.helloworldapp.ui.viewmodel

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _user1Prefs = mutableStateMapOf<String, Boolean>()
    private val _user2Prefs = mutableStateMapOf<String, Boolean>()

    val user1Prefs: Map<String, Boolean> get() = _user1Prefs
    val user2Prefs: Map<String, Boolean> get() = _user2Prefs

    fun toggleUser1Pref(pref: String) {
        _user1Prefs[pref] = !(_user1Prefs[pref] ?: false)
    }

    fun toggleUser2Pref(pref: String) {
        _user2Prefs[pref] = !(_user2Prefs[pref] ?: false)
    }

    fun clearPrefs() {
        _user1Prefs.clear()
        _user2Prefs.clear()
    }
}