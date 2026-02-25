package com.anjegonz.bikeworkshop.garage.presentation.motorcycle_display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anjegonz.bikeworkshop.garage.domain.MotorcycleRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.Year

class MotorcycleDisplayViewModel(
    private val motorcycleRepository: MotorcycleRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MotorcycleDisplayState())


    private val _navigateBack = MutableSharedFlow<Unit>()
    val navigateBack = _navigateBack.asSharedFlow()
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = MotorcycleDisplayState()
        )

    fun onAction(action: MotorcycleDisplayAction) {
        when (action) {
            is MotorcycleDisplayAction.onMotorcycleClick -> {
                _state.update {
                    it.copy(
                        mainMotorcycle = action.motorcycle
                    )
                }
            }
            is MotorcycleDisplayAction.onMotorcycleDeleteClick -> {
                deleteMotorcycle()
            }
        }
    }


    fun observeMotorcyclesByManufacturer(manufacturer: String, id: Int) {
        motorcycleRepository.getMotorcyclesByManufacturer(manufacturer)
            .onEach { motorcycles ->
                val displayedMotorcycle = motorcycles.find { it.id == id }
                _state.update {
                    it.copy(
                        motorcycleCarousel = motorcycles,
                        mainMotorcycle = displayedMotorcycle,
                        ageOfMotorcycle = displayedMotorcycle?.let { motorcycle ->
                            Year.now().value - motorcycle.yearOfConstruction
                        }?:0
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun deleteMotorcycle(){
        viewModelScope.launch{
            _state.value.mainMotorcycle?.let {motorcycle ->
                motorcycleRepository.deleteMotorcycle(motorcycle)
            }
            _navigateBack.emit(Unit)
        }
    }

}