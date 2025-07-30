package com.example.helloworldapp.viewmodel

import SortColumn
import SortState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldapp.data.AppDatabase // Ensure this is imported
import com.example.helloworldapp.data.ItemEntity
import com.example.helloworldapp.data.VendorWithItems
import com.example.helloworldapp.model.Preference
//import com.example.helloworldapp.model.SortColumn // To be uncommented later
//import com.example.helloworldapp.model.SortDirection // To be uncommented later
//import com.example.helloworldapp.model.SortState // To be uncommented later
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class SharedViewModel : ViewModel() {

    private val _user1Prefs = MutableStateFlow<Set<Preference>>(emptySet())
    val user1Prefs: StateFlow<Set<Preference>> = _user1Prefs.asStateFlow()

    private val _user2Prefs = MutableStateFlow<Set<Preference>>(emptySet())
    val user2Prefs: StateFlow<Set<Preference>> = _user2Prefs.asStateFlow()

    // Holds the fully processed and sorted list before pagination
    private val _fullDisplayVendors = MutableStateFlow<List<DisplayVendor>>(emptyList())

    // This is what the UI will observe for the paginated list
    private val _pagedVendors = MutableStateFlow<List<DisplayVendor>>(emptyList())
    val pagedVendors: StateFlow<List<DisplayVendor>> = _pagedVendors.asStateFlow()

    private val _totalResultsCount = MutableStateFlow(0)
    val totalResultsCount: StateFlow<Int> = _totalResultsCount.asStateFlow()

    private val _visibleRange = MutableStateFlow(0 to 0)
    val visibleRange: StateFlow<Pair<Int, Int>> = _visibleRange.asStateFlow()

    private var currentPage = 0
    private val pageSize = 10 // You can adjust page size

     private val _sortState = MutableStateFlow(SortState()) // Default sort
     val sortState: StateFlow<SortState> = _sortState.asStateFlow()

    fun updateVisibleRange(start: Int, end: Int) {
        _visibleRange.value = start to end
    }

    fun toggleUser1Pref(pref: Preference) {
        val currentPrefs = _user1Prefs.value.toMutableSet()
        if (pref in currentPrefs) {
            currentPrefs.remove(pref)
        } else {
            currentPrefs.add(pref)
        }
        _user1Prefs.value = currentPrefs
    }

    fun toggleUser2Pref(pref: Preference) {
        val currentPrefs = _user2Prefs.value.toMutableSet()
        if (pref in currentPrefs) {
            currentPrefs.remove(pref)
        } else {
            currentPrefs.add(pref)
        }
        _user2Prefs.value = currentPrefs
    }

    fun clearPrefs() {
        _user1Prefs.value = emptySet()
        _user2Prefs.value = emptySet()
        // Optionally re-trigger computation if needed, or let UI decide
        // For example, if you want search results to clear or update:
        // loadAndComputeResults(yourDbInstance) // This needs a db instance
    }

    fun updateSortState(column: SortColumn) {
        val currentSort = _sortState.value
        val newDirection = if (currentSort.column == column) {
            if (currentSort.direction == SortDirection.ASCENDING) SortDirection.DESCENDING else SortDirection.ASCENDING
        } else {
            // Default direction for new columns (e.g., DESCENDING for rating/items, ASCENDING for distance)
            when (column) {
                SortColumn.VENDOR_RATING -> SortDirection.DESCENDING
                SortColumn.MENU_ITEMS -> SortDirection.DESCENDING
                SortColumn.DISTANCE -> SortDirection.ASCENDING
            }
        }
        _sortState.value = SortState(column, newDirection)
        // Re-apply sort and pagination
        applySortAndPagination(_fullDisplayVendors.value)
    }


    fun loadAndComputeResults(db: AppDatabase) {
        viewModelScope.launch {
            val allVendorsWithItems = db.vendorDao().getVendorsWithItems()
            computeAndProcessResults(allVendorsWithItems)
        }
    }

    private fun computeAndProcessResults(allVendorsWithItems: List<VendorWithItems>) {
        val u1Prefs = _user1Prefs.value
        val u2Prefs = _user2Prefs.value

        val isUser1Active = u1Prefs.isNotEmpty()
        val isUser2Active = u2Prefs.isNotEmpty()

        val processedVendors = allVendorsWithItems.mapNotNull { vendorWithItems ->
            val vendor = vendorWithItems.vendor
            val items = vendorWithItems.items

            // --- Per-user item matching ---
            val user1MatchingItems = if (isUser1Active) items.filter { item -> matchesPrefs(u1Prefs, item) } else emptyList()
            val user2MatchingItems = if (isUser2Active) items.filter { item -> matchesPrefs(u2Prefs, item) } else emptyList()

            // --- Combined relevant items for rating and count ---
            val relevantItemsForQuery = when {
                isUser1Active && isUser2Active -> (user1MatchingItems + user2MatchingItems).distinct()
                isUser1Active -> user1MatchingItems
                isUser2Active -> user2MatchingItems
                else -> items // If no prefs, consider all items for rating.
            }

            if ((isUser1Active || isUser2Active) && relevantItemsForQuery.isEmpty()) {
                null // Skip this vendor if prefs are active but no items match
            } else {
                // --- Calculate Query Specific Rating (String and Value) ---
                val sumApplicableUpvotes = relevantItemsForQuery.sumOf { it.upvotes }
                val sumApplicableTotalVotes = relevantItemsForQuery.sumOf { it.totalVotes }

                val querySpecificRatingString: String
                val querySpecificRatingValue: Float

                if (sumApplicableTotalVotes > 0) {
                    querySpecificRatingString = "$sumApplicableUpvotes / $sumApplicableTotalVotes"
                    querySpecificRatingValue = sumApplicableUpvotes.toFloat() / sumApplicableTotalVotes.toFloat()
                } else {
                    querySpecificRatingString = "0 / 0" // Or "N/A" or as you prefer
                    querySpecificRatingValue = 0f       // Or -1f to indicate no rating possible
                }

                // --- Combined Relevant Item Count for Display ---
                val combinedRelevantItemCount = relevantItemsForQuery.size

                DisplayVendor(
                    vendorName = vendor.name,
                    user1Count = user1MatchingItems.size,
                    user2Count = user2MatchingItems.size,
                    distanceMiles = vendor.lat / 1000.0, // REMINDER: Replace with actual distance
                    querySpecificRatingString = querySpecificRatingString, // Use new field
                    querySpecificRatingValue = querySpecificRatingValue,   // Use new field
                    combinedRelevantItemCount = combinedRelevantItemCount
                )
            }
        }

        _fullDisplayVendors.value = processedVendors
        _totalResultsCount.value = processedVendors.size
        // applySortAndPagination(processedVendors) // Will be called when sorting is active
        resetAndLoadFirstPage(processedVendors) // Load first page without sorting for now
    }


    private fun applySortAndPagination(vendors: List<DisplayVendor>) {
        // --- SORTING LOGIC --- (Uncomment and adapt when SortType.kt is ready)
        /*
        val sortedVendors = when (sortState.value.column) {
            SortColumn.VENDOR_RATING -> {
                if (sortState.value.direction == SortDirection.ASCENDING) {
                    vendors.sortedBy { it.querySpecificRating }
                } else {
                    vendors.sortedByDescending { it.querySpecificRating }
                }
            }
            SortColumn.DISTANCE -> {
                if (sortState.value.direction == SortDirection.ASCENDING) {
                    vendors.sortedBy { it.distanceMiles }
                } else {
                    vendors.sortedByDescending { it.distanceMiles }
                }
            }
            SortColumn.MENU_ITEMS -> {
                if (sortState.value.direction == SortDirection.ASCENDING) {
                    vendors.sortedBy { it.combinedRelevantItemCount }
                } else {
                    vendors.sortedByDescending { it.combinedRelevantItemCount }
                }
            }
        }
        _fullDisplayVendors.value = sortedVendors // Update the full list with sorted order
        resetAndLoadFirstPage(sortedVendors)
        */
        // For now, without sorting:
        resetAndLoadFirstPage(vendors)
    }


    private fun resetAndLoadFirstPage(vendors: List<DisplayVendor>) {
        currentPage = 0
        _pagedVendors.value = vendors.take(pageSize)
        _totalResultsCount.value = vendors.size // Ensure total count reflects the potentially filtered list
    }

    fun loadNextPage() {
        if ((currentPage + 1) * pageSize < _fullDisplayVendors.value.size) {
            currentPage++
            val currentPaged = _pagedVendors.value.toMutableList()
            val nextPageItems = _fullDisplayVendors.value.drop(currentPage * pageSize).take(pageSize)
            currentPaged.addAll(nextPageItems)
            _pagedVendors.value = currentPaged
        }
    }

    private fun matchesPrefs(prefs: Set<Preference>, item: ItemEntity): Boolean {
        if (prefs.isEmpty()) return true // If no preferences, item is considered a match (or handle as needed)
        return prefs.all { pref ->
            // Special handling for LOW_PRICE if it were to be a filter
            // if (pref == Preference.LOW_PRICE) return true // Or some price logic
            pref.matcher?.invoke(item) ?: true // If matcher is null, it's not a filter criteria (like LOW_PRICE)
        }
    }
}