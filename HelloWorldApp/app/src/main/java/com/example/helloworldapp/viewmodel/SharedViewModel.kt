package com.example.helloworldapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldapp.data.AppDatabase
import com.example.helloworldapp.data.ItemEntity
import com.example.helloworldapp.data.VendorWithItems
import com.example.helloworldapp.model.Preference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val _user1Prefs = MutableStateFlow<Set<Preference>>(emptySet())
    val user1Prefs: StateFlow<Set<Preference>> = _user1Prefs

    private val _user2Prefs = MutableStateFlow<Set<Preference>>(emptySet())
    val user2Prefs: StateFlow<Set<Preference>> = _user2Prefs

    private val _displayVendors = MutableStateFlow<List<DisplayVendor>>(emptyList())
    val displayVendors: StateFlow<List<DisplayVendor>> = _displayVendors

    //private val _sortState = MutableStateFlow(SortState()) // Default sort by Menu Items, Descending
    //val sortState: StateFlow<SortState> = _sortState.asStateFlow()
    private val _totalResultsCount = MutableStateFlow(0)
    val totalResultsCount: StateFlow<Int> = _totalResultsCount

    private val _visibleRange = MutableStateFlow(0 to 0)
    val visibleRange: StateFlow<Pair<Int, Int>> = _visibleRange

    private val _pagedVendors = MutableStateFlow<List<DisplayVendor>>(emptyList())
    val pagedVendors: StateFlow<List<DisplayVendor>> = _pagedVendors

    private var currentPage = 0
    private val pageSize = 10

    //private val _sortedDisplayVendors = MutableStateFlow<List<DisplayVendor>>(emptyList())


    fun updateVisibleRange(start: Int, end: Int) {
        _visibleRange.value = start to end
    }

    fun toggleUser1Pref(pref: Preference) {
        _user1Prefs.value = _user1Prefs.value.toMutableSet().apply {
            if (!add(pref)) remove(pref)
        }
    }

    fun toggleUser2Pref(pref: Preference) {
        _user2Prefs.value = _user2Prefs.value.toMutableSet().apply {
            if (!add(pref)) remove(pref)
        }
    }

    fun clearPrefs() {
        _user1Prefs.value = emptySet()
        _user2Prefs.value = emptySet()
    }

    fun loadAndComputeResults(db: AppDatabase) {
        viewModelScope.launch {
            val allVendorsWithItems = db.vendorDao().getVendorsWithItems()
//            Log.d("FilterDebug", "Loaded vendors from DB: ${allVendorsWithItems.size}")
//            Log.d("FilterDebug", "User1 active prefs: ${user1Prefs.value.filterValues { it }}")
//            Log.d("FilterDebug", "User2 active prefs: ${user2Prefs.value.filterValues { it }}")

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
        _totalResultsCount.value = finalResults.size
        // Reset pagination
        currentPage = 0
        _pagedVendors.value = finalResults.take(pageSize)
    }

    fun loadNextPage() {
        val allResults = _displayVendors.value
        val nextPage = currentPage + 1
        val endIndex = (nextPage + 1) * pageSize

        if (endIndex <= allResults.size) {
            _pagedVendors.value = allResults.take(endIndex)
            currentPage = nextPage
        }
    }

    private fun matchesPrefs(prefs: Set<Preference>, item: ItemEntity): Boolean {
        return prefs.all { it.matches(item) }
    }
}
