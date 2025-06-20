// SharedViewModel.kt
package com.example.helloworldapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SharedViewModel : ViewModel() {
    private val _user1Prefs = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val user1Prefs: StateFlow<Map<String, Boolean>> = _user1Prefs

    private val _user2Prefs = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val user2Prefs: StateFlow<Map<String, Boolean>> = _user2Prefs

    fun toggleUser1Pref(pref: String) {
        _user1Prefs.value = _user1Prefs.value.toMutableMap().also {
            it[pref] = !(it[pref] ?: false)
        }
    }

    fun toggleUser2Pref(pref: String) {
        _user2Prefs.value = _user2Prefs.value.toMutableMap().also {
            it[pref] = !(it[pref] ?: false)
        }
    }

    fun clearPrefs() {
        _user1Prefs.value = emptyMap()
        _user2Prefs.value = emptyMap()
    }
}