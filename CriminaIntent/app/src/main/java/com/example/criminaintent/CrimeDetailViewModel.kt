package com.example.criminaintent

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CrimeDetailViewModel(crimeId: UUID) {
    private val crimeRepository = CrimeRepository.get()

    private val _crime: MutableStateFlow<Crime?> = MutableStateFlow(null)
    val crime: StateFlow<Crime?> = _crime.asStateFlow()

    init{
        viewModelScope.launch {
            _crime.value = crimeRepository.getCrime(crimeId)
        }
    }
}