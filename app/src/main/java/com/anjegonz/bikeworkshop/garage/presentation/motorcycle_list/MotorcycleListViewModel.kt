package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MotorcycleListViewModel(
    private val motorcycleRepository: MotorcycleRepository
): ViewModel() {

    private var hasLoadedInitialData = false

    private var observeMotorcyclesJob: Job? = null

    private val _state = MutableStateFlow(MotorcycleListState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                observeMotorcycles()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MotorcycleListState()
        )
    //Funny that we won't be needing an "onAction" in this viewmodel

    private fun observeMotorcycles(){
        observeMotorcyclesJob?.cancel()
        observeMotorcyclesJob = motorcycleRepository.getMotorcycles()
            .onEach { motorcycles ->
                _state.update { it.copy(
                    motorcycles = motorcycles
                ) }
            }
            .launchIn(viewModelScope)
    }
}