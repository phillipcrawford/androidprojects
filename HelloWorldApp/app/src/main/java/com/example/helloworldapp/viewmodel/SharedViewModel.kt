package com.example.helloworldapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldapp.data.AppDatabase
import com.example.helloworldapp.data.ItemEntity
import com.example.helloworldapp.data.VendorWithItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log

data class DisplayVendor(
    val vendorName: String,
    val user1Count: Int,
    val user2Count: Int
)

class SharedViewModel : ViewModel() {
    private val _user1Prefs = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val user1Prefs: StateFlow<Map<String, Boolean>> = _user1Prefs

    private val _user2Prefs = MutableStateFlow<Map<String, Boolean>>(emptyMap())
    val user2Prefs: StateFlow<Map<String, Boolean>> = _user2Prefs

    private val _displayVendors = MutableStateFlow<List<DisplayVendor>>(emptyList())
    val displayVendors: StateFlow<List<DisplayVendor>> = _displayVendors

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

    fun loadAndComputeResults(db: AppDatabase) {
        viewModelScope.launch {
            val allVendorsWithItems = db.vendorDao().getVendorsWithItems()
            computeResults(allVendorsWithItems)
        }
    }

    private fun computeResults(allVendorsWithItems: List<VendorWithItems>) {
        val user1Results = mutableMapOf<String, Int>()
        val user2Results = mutableMapOf<String, Int>()

        for (vendorWithItems in allVendorsWithItems) {
            val vendorName = vendorWithItems.vendor.name
            val items = vendorWithItems.items
            var user1Count = 0
            var user2Count = 0

            for (item in items) {
                if (matchesPrefs(_user1Prefs.value, item)) user1Count++
                if (matchesPrefs(_user2Prefs.value, item)) user2Count++
            }

            if (user1Count > 0) user1Results[vendorName] = user1Count
            if (user2Count > 0) user2Results[vendorName] = user2Count
        }

        val finalResults = mutableListOf<DisplayVendor>()

        if (_user1Prefs.value.isNotEmpty() && _user2Prefs.value.isNotEmpty()) {
            val commonVendors = user1Results.keys.intersect(user2Results.keys)
            for (vendor in commonVendors) {
                finalResults.add(
                    DisplayVendor(
                        vendorName = vendor,
                        user1Count = user1Results[vendor] ?: 0,
                        user2Count = user2Results[vendor] ?: 0
                    )
                )
            }
        } else if (_user1Prefs.value.isNotEmpty()) {
            for ((vendor, count) in user1Results) {
                finalResults.add(DisplayVendor(vendor, user1Count = count, user2Count = 0))
            }
        } else if (_user2Prefs.value.isNotEmpty()) {
            for ((vendor, count) in user2Results) {
                finalResults.add(DisplayVendor(vendor, user1Count = 0, user2Count = count))
            }
        }

        _displayVendors.value = finalResults
    }

    private fun matchesPrefs(prefs: Map<String, Boolean>, item: ItemEntity): Boolean {
        for ((key, value) in prefs) {
            if (value) {
                when (key) {
                    "vegetarian" -> if (!item.vegetarian) return false
                    "pescetarian" -> if (!item.pescetarian) return false
                    "vegan" -> if (!item.vegan) return false
                    "keto" -> if (!item.keto) return false
                    "organic" -> if (!item.organic) return false
                    "gmoFree" -> if (!item.gmoFree) return false
                    "locallySourced" -> if (!item.locallySourced) return false
                    "raw" -> if (!item.raw) return false
                    "entree" -> if (!item.entree) return false
                    "sweet" -> if (!item.sweet) return false
                    "kosher" -> if (!item.kosher) return false
                    "halal" -> if (!item.halal) return false
                    "beef" -> if (!item.beef) return false
                    "chicken" -> if (!item.chicken) return false
                    "pork" -> if (!item.pork) return false
                    "seafood" -> if (!item.seafood) return false
                    "lowSugar" -> if (!item.lowSugar) return false
                    "highProtein" -> if (!item.highProtein) return false
                    "lowCarb" -> if (!item.lowCarb) return false
                    "noAlliums" -> if (!item.noAlliums) return false
                    "noPorkProducts" -> if (!item.noPorkProducts) return false
                    "noRedMeat" -> if (!item.noRedMeat) return false
                    "noMsg" -> if (!item.noMsg) return false
                    "noSesame" -> if (!item.noSesame) return false
                    "noMilk" -> if (!item.noMilk) return false
                    "noEggs" -> if (!item.noEggs) return false
                    "noFish" -> if (!item.noFish) return false
                    "noShellfish" -> if (!item.noShellfish) return false
                    "noPeanuts" -> if (!item.noPeanuts) return false
                    "noTreenuts" -> if (!item.noTreenuts) return false
                    "glutenFree" -> if (!item.glutenFree) return false
                    "noSoy" -> if (!item.noSoy) return false
                }
            }
        }
        return true
    }
}
